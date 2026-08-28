package com.agentstack.lawguard.tools;

import com.agentstack.lawguard.rag.KnowledgeBaseService;
import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainTools {

    private static final int TOP_K = 5;
    private static final List<String> HIGH_RISK_KEYWORDS = List.of(
            "违约金", "赔偿", "免责", "保密", "单方解除", "仲裁", "上限", "自动续约"
    );

    private final KnowledgeBaseService knowledgeBaseService;

    public DomainTools(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @Tool(description = "Search tenant scoped private knowledge base")
    public String knowledge_search(@ToolParam(description = "search query") String query) {
        return format(knowledgeBaseService.search(query, TOP_K));
    }

    @Tool(description = "检索企业法规库（法条/规章制度），返回带来源引用的片段")
    public String regulation_rag_search(@ToolParam(description = "business query") String query) {
        return format(knowledgeBaseService.search(query, "regulation", TOP_K));
    }

    @Tool(description = "检索历史判例/类案库，返回带来源引用的片段")
    public String case_law_search(@ToolParam(description = "business query") String query) {
        return format(knowledgeBaseService.search(query, "case", TOP_K));
    }

    @Tool(description = "从合同模板库中提取相关条款片段")
    public String contract_clause_extract(@ToolParam(description = "business query") String query) {
        return format(knowledgeBaseService.search(query, "contract_template", TOP_K));
    }

    @Tool(description = "对合同相关提问做条款风险检查：检索合同模板库并在命中片段上叠加高风险关键词规则，输出潜在风险点（需人工复核）")
    public String clause_risk_check(@ToolParam(description = "business query") String query) {
        List<Document> hits = knowledgeBaseService.search(query, "contract_template", TOP_K);
        StringBuilder sb = new StringBuilder();
        if (hits.isEmpty()) {
            sb.append("未在合同模板库中找到相关内容。\n");
        } else {
            for (Document doc : hits) {
                sb.append("- 【来源: ").append(meta(doc, "source_uri")).append("】")
                        .append(snippet(doc)).append("\n");
            }
        }
        String text = hits.stream().map(Document::getText).filter(t -> t != null)
                .collect(Collectors.joining("\n"));
        List<String> flags = HIGH_RISK_KEYWORDS.stream().filter(text::contains).toList();
        if (flags.isEmpty()) {
            sb.append("未在命中条款中检出常见高风险关键词（违约金/赔偿/免责/保密/单方解除/仲裁/上限/自动续约）。");
        } else {
            sb.append("检出潜在风险关键词: ").append(String.join("、", flags))
                    .append("。请结合完整合同原文及法律意见人工复核。");
        }
        return sb.toString();
    }

    @Tool(description = "将检索结果整理为可追溯的法条/案例/条款引用列表")
    public String citation_builder(@ToolParam(description = "business query") String query) {
        List<Document> docs = knowledgeBaseService.search(query, TOP_K);
        if (docs.isEmpty()) {
            return "无可用引用，检索未命中。";
        }
        return docs.stream()
                .map(doc -> "- [" + meta(doc, "source_type") + "] "
                        + meta(doc, "title") + " @ " + meta(doc, "source_uri"))
                .distinct()
                .collect(Collectors.joining("\n"));
    }

    @Tool(description = "合规免责声明")
    public String compliance_disclaimer() {
        return "免责声明：本输出由 AI 生成，仅供法律合规研究参考，不构成正式法律意见；"
                + "重要决策请由执业律师复核并核对原始法条/合同文本。";
    }

    private static String format(List<Document> docs) {
        if (docs.isEmpty()) {
            return "未在知识库中找到相关内容。";
        }
        return docs.stream()
                .map(doc -> "- 【来源: " + meta(doc, "source_uri") + "】" + snippet(doc))
                .collect(Collectors.joining("\n"));
    }

    private static String snippet(Document doc) {
        String text = doc.getText();
        if (text == null || text.isBlank()) {
            return "(无文本内容)";
        }
        return text.length() > 500 ? text.substring(0, 500) + "…" : text;
    }

    private static String meta(Document doc, String key) {
        Object value = doc.getMetadata() == null ? null : doc.getMetadata().get(key);
        return value == null ? "" : String.valueOf(value);
    }

}