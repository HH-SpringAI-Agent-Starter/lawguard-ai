package com.agentstack.lawguard.controller;

import com.agentstack.lawguard.agent.AgentService;
import com.agentstack.lawguard.dto.AgentRequest;
import com.agentstack.lawguard.dto.AgentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/ask")
    public AgentResponse ask(@RequestBody AgentRequest request) {
        return agentService.ask(request);
    }
}
