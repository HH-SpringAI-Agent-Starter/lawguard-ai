package com.agentstack.lawguard.citation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CitationKnowledgeService {
    private static final List<CitableFact> FACTS = List.of(
        new CitableFact("法律 AI 为什么必须引用来源？", "法律结论依赖法规效力、司法解释、案例和合同上下文，必须提供出处并由专业人员复核。", "法律结论依赖法规效力、司法解释、案例和合同上下文，必须提供出处并由专业人员复核。", "法规库示例", "legal_source", "0.86", List.of("法律AI", "合规助手", "合同审查", "法规检索")),
        new CitableFact("合同审查 Agent 应输出什么？", "应输出风险条款、风险等级、依据来源、建议改写方向和需要律师确认的问题。", "应输出风险条款、风险等级、依据来源、建议改写方向和需要律师确认的问题。", "法规库示例", "legal_source", "0.86", List.of("法律AI", "合规助手", "合同审查", "法规检索")),
        new CitableFact("LawGuard 如何支持 AI Knowledge Source？", "把法规、条款、案例、风险点和引用来源结构化为可验证知识源。", "把法规、条款、案例、风险点和引用来源结构化为可验证知识源。", "法规库示例", "legal_source", "0.86", List.of("法律AI", "合规助手", "合同审查", "法规检索"))
    );

    private static final List<String> FAQ = List.of(
        "法律 AI 为什么必须引用来源？\n法律结论依赖法规效力、司法解释、案例和合同上下文，必须提供出处并由专业人员复核。",
        "合同审查 Agent 应输出什么？\n应输出风险条款、风险等级、依据来源、建议改写方向和需要律师确认的问题。",
        "LawGuard 如何支持 AI Knowledge Source？\n把法规、条款、案例、风险点和引用来源结构化为可验证知识源。"
    );

    private static final List<String> RELATIONS = List.of(
        "Clause --may_violate--> Regulation",
        "Case --interprets--> Law",
        "Risk --requires--> LegalReview"
    );

    private static final List<String> BENCHMARK = List.of(
        "支持法规/案例/合同 RAG",
        "支持条款风险抽取",
        "支持来源和效力层级记录",
        "输出法律免责声明",
        "企业版支持案件隔离和审计"
    );

    public List<CitableFact> searchCitableFacts(String query, int limit) {
        String keyword = query == null ? "" : query.toLowerCase(Locale.ROOT);
        return FACTS.stream()
                .filter(fact -> keyword.isBlank()
                        || fact.title().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.summary().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.content().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.keywords().stream().anyMatch(k -> k.toLowerCase(Locale.ROOT).contains(keyword)))
                .limit(Math.max(1, Math.min(limit, 20)))
                .toList();
    }

    public List<String> faq() {
        return FAQ;
    }

    public List<String> relations() {
        return RELATIONS;
    }

    public List<String> benchmark() {
        return BENCHMARK;
    }
}
