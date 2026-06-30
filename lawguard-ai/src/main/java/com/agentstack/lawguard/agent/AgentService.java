package com.agentstack.lawguard.agent;

import com.agentstack.lawguard.dto.AgentRequest;
import com.agentstack.lawguard.dto.AgentResponse;
import com.agentstack.lawguard.tools.DomainTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AgentService {
    private final ChatClient chatClient;
    private final DomainTools domainTools;

    public AgentService(ChatClient chatClient, DomainTools domainTools) {
        this.chatClient = chatClient;
        this.domainTools = domainTools;
    }

    public AgentResponse ask(AgentRequest request) {
        String prompt = """
                场景：法律/合规助手
                用户问题：%s

                请按以下格式回答：
                1. 结论
                2. 依据/引用
                3. 需要调用或已经调用的工具
                4. 风险提示
                5. 下一步建议
                """.formatted(request.question());

        String answer = chatClient.prompt()
                .user(prompt)
                .tools(domainTools)
                .call()
                .content();

        return new AgentResponse(
                answer,
                List.of("kb://demo/sample"),
                List.of("regulation_rag_search", "case_law_search", "contract_clause_extract", "clause_risk_check", "citation_builder", "compliance_disclaimer"),
                "medium",
                UUID.randomUUID().toString()
        );
    }
}
