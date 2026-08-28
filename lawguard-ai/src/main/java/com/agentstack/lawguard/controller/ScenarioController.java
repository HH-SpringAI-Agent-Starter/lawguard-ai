package com.agentstack.lawguard.controller;

import com.agentstack.lawguard.agent.AgentService;
import com.agentstack.lawguard.dto.AgentRequest;
import com.agentstack.lawguard.dto.AgentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScenarioController {

    private final AgentService agentService;

    public ScenarioController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/contracts/review")
    public AgentResponse reviewContract(@RequestBody AgentRequest request) {
        return agentService.ask(withPrefix(request, "【合同审查】请以合同审查视角回答: "));
    }

    @PostMapping("/compliance/check")
    public AgentResponse checkCompliance(@RequestBody AgentRequest request) {
        return agentService.ask(withPrefix(request, "【合规检查】请以合规风险评估视角回答: "));
    }

    private AgentRequest withPrefix(AgentRequest request, String prefix) {
        return new AgentRequest(
                prefix + request.question(),
                request.userId(),
                request.sessionId(),
                request.tenantId(),
                request.context()
        );
    }

}