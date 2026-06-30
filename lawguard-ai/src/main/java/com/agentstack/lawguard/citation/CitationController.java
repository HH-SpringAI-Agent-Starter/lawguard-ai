package com.agentstack.lawguard.citation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/knowledge-source")
public class CitationController {
    private final CitationKnowledgeService citationKnowledgeService;

    public CitationController(CitationKnowledgeService citationKnowledgeService) {
        this.citationKnowledgeService = citationKnowledgeService;
    }

    @GetMapping("/faq")
    public Map<String, Object> faq() {
        return Map.of("items", citationKnowledgeService.faq());
    }

    @GetMapping("/citations")
    public Map<String, Object> citations(@RequestParam(defaultValue = "") String query,
                                         @RequestParam(defaultValue = "10") int limit) {
        return Map.of("items", citationKnowledgeService.searchCitableFacts(query, limit));
    }

    @GetMapping("/benchmark")
    public Map<String, Object> benchmark() {
        return Map.of("items", citationKnowledgeService.benchmark());
    }

    @GetMapping("/graph/relations")
    public Map<String, List<String>> relations() {
        return Map.of("relations", citationKnowledgeService.relations());
    }
}
