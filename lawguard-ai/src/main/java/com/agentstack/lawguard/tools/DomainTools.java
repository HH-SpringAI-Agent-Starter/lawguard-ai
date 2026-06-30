package com.agentstack.lawguard.tools;

import com.agentstack.lawguard.rag.KnowledgeBaseService;
import com.agentstack.lawguard.tenant.TenantContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class DomainTools {
    private final KnowledgeBaseService knowledgeBaseService;

    public DomainTools(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @Tool(description = "Search tenant scoped private knowledge base")
    public String knowledge_search(@ToolParam(description = "search query") String query) {
        return String.join("\n", knowledgeBaseService.search(query));
    }

    @Tool(description = "regulation rag search for 法律/合规助手")
    public String regulation_rag_search(@ToolParam(description = "business query") String query) {
        return "[regulation_rag_search] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "case law search for 法律/合规助手")
    public String case_law_search(@ToolParam(description = "business query") String query) {
        return "[case_law_search] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "contract clause extract for 法律/合规助手")
    public String contract_clause_extract(@ToolParam(description = "business query") String query) {
        return "[contract_clause_extract] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "clause risk check for 法律/合规助手")
    public String clause_risk_check(@ToolParam(description = "business query") String query) {
        return "[clause_risk_check] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "citation builder for 法律/合规助手")
    public String citation_builder(@ToolParam(description = "business query") String query) {
        return "[citation_builder] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "compliance disclaimer for 法律/合规助手")
    public String compliance_disclaimer(@ToolParam(description = "business query") String query) {
        return "[compliance_disclaimer] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

}
