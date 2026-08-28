package com.agentstack.lawguard.controller;

import com.agentstack.lawguard.rag.KnowledgeBaseService;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kb")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @PostMapping("/sync")
    public Map<String, Object> sync(@RequestBody Map<String, Object> body) {
        String content = String.valueOf(body.getOrDefault("content", ""));
        String sourceType = String.valueOf(body.getOrDefault("sourceType", "manual"));
        String sourceUri = String.valueOf(body.getOrDefault("sourceUri", "manual://" + System.currentTimeMillis()));
        String title = String.valueOf(body.getOrDefault("title", sourceUri));
        if (content.isBlank()) {
            return Map.of("status", "rejected", "reason", "content is required");
        }
        knowledgeBaseService.ingest(content, sourceType, sourceUri, title);
        return Map.of("status", "accepted", "sourceType", sourceType, "sourceUri", sourceUri, "title", title);
    }

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam String q,
                                      @RequestParam(required = false) String type,
                                      @RequestParam(defaultValue = "5") int topK) {
        List<Document> results = knowledgeBaseService.search(q, type, topK);
        List<Map<String, Object>> items = results.stream().map(doc -> Map.of(
                "content", snippet(doc),
                "sourceType", meta(doc, "source_type"),
                "sourceUri", meta(doc, "source_uri"),
                "title", meta(doc, "title"),
                "score", doc.getScore() == null ? "n/a" : doc.getScore().toString()
        )).toList();
        return Map.of("count", items.size(), "results", items);
    }

    private static String snippet(Document doc) {
        String text = doc.getText();
        if (text == null) {
            return "";
        }
        return text.length() > 500 ? text.substring(0, 500) + "…" : text;
    }

    private static String meta(Document doc, String key) {
        Object value = doc.getMetadata() == null ? null : doc.getMetadata().get(key);
        return value == null ? "" : String.valueOf(value);
    }

}