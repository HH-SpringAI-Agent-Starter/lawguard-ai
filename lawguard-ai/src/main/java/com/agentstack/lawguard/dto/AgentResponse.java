package com.agentstack.lawguard.dto;

import java.util.List;

public record AgentResponse(
        String answer,
        List<String> citations,
        List<String> toolCalls,
        String riskLevel,
        String traceId
) {}
