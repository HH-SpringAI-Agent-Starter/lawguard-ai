package com.agentstack.lawguard.tools;

import com.agentstack.lawguard.rag.KnowledgeBaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.document.Document;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * DomainTools 六个领域工具的单元测试（KnowledgeBaseService 以 Mock 隔离，不依赖数据库）。
 * 覆盖：知识库未命中兜底文案、高风险关键词检出、引用列表构建、免责声明。
 */
@ExtendWith(MockitoExtension.class)
class DomainToolsTest {

    @Mock
    private KnowledgeBaseService knowledgeBaseService;

    private DomainTools tools() {
        return new DomainTools(knowledgeBaseService);
    }

    @Test
    void knowledgeSearchReturnsFallbackWhenNoHits() {
        when(knowledgeBaseService.search(anyString(), eq(5))).thenReturn(List.of());
        assertThat(tools().knowledge_search("不存在的问题"))
                .isEqualTo("未在知识库中找到相关内容。");
    }

    @Test
    void regulationSearchUsesRegulationSourceType() {
        Document doc = document("regulation", "《劳动法》第X条", "regulation://labor-law", "用人单位不得随意单方解除合同。");
        when(knowledgeBaseService.search(anyString(), eq("regulation"), eq(5))).thenReturn(List.of(doc));
        String out = tools().regulation_rag_search("单方解除有何规定");
        assertThat(out).contains("regulation://labor-law").contains("用人单位");
    }

    @Test
    void clauseRiskCheckFlagsHighRiskKeywords() {
        Document doc = document("contract_template", "竞业限制协议", "contract://nd", "员工离职后两年内不得从事竞业，违约金为年收入的300%。");
        when(knowledgeBaseService.search(anyString(), eq("contract_template"), eq(5))).thenReturn(List.of(doc));
        String out = tools().clause_risk_check("审查竞业限制条款");
        assertThat(out).contains("违约金").contains("人工复核");
    }

    @Test
    void clauseRiskCheckReportsNoKeywordsWhenClean() {
        Document doc = document("contract_template", "普通采购合同", "contract://po", "双方协商一致可变更合同内容。");
        when(knowledgeBaseService.search(anyString(), eq("contract_template"), eq(5))).thenReturn(List.of(doc));
        String out = tools().clause_risk_check("审查采购合同");
        assertThat(out).contains("未在命中条款中检出常见高风险关键词");
    }

    @Test
    void clauseRiskCheckFallbackWhenNoTemplateHits() {
        when(knowledgeBaseService.search(anyString(), eq("contract_template"), eq(5))).thenReturn(List.of());
        assertThat(tools().clause_risk_check("审查未知条款"))
                .contains("未在合同模板库中找到相关内容");
    }

    @Test
    void citationBuilderDeduplicatesAndListsSources() {
        Document caseDoc = document("case", "张某诉某公司案", "case://c1", "判决支持违约金酌减。");
        Document regDoc = document("regulation", "《民法典》第585条", "regulation://r1", "违约金过高可请求酌减。");
        when(knowledgeBaseService.search(anyString(), eq(5))).thenReturn(List.of(caseDoc, regDoc));
        String out = tools().citation_builder("违约金酌减依据");
        assertThat(out).contains("[case] 张某诉某公司案 @ case://c1")
                .contains("[regulation] 《民法典》第585条 @ regulation://r1");
    }

    @Test
    void citationBuilderReturnsMessageWhenNoDocs() {
        when(knowledgeBaseService.search(anyString(), eq(5))).thenReturn(List.of());
        assertThat(tools().citation_builder("无结果查询")).isEqualTo("无可用引用，检索未命中。");
    }

    @Test
    void disclaimerAlwaysPresent() {
        assertThat(tools().compliance_disclaimer())
                .contains("免责声明").contains("不构成正式法律意见");
    }

    private static Document document(String sourceType, String title, String uri, String text) {
        return new Document(text, Map.of(
                "source_type", sourceType,
                "title", title,
                "source_uri", uri
        ));
    }
}