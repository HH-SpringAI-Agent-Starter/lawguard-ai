package com.agentstack.lawguard.controller;

import com.agentstack.lawguard.rag.KnowledgeBaseService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/kb")
public class KnowledgeBaseController {
    private final KnowledgeBaseService knowledgeBaseService;
    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) { this.knowledgeBaseService = knowledgeBaseService; }

    @PostMapping("/sync")
    public Map<String, Object> sync(@RequestBody Map<String, Object> body) {
        // TODO: implement document loader, chunking, embedding and PGVector upsert.
        return Map.of("status", "accepted", "source", body.getOrDefault("source", "manual"));
    }

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam String q) {
        return Map.of("results", knowledgeBaseService.search(q));
    }
}
