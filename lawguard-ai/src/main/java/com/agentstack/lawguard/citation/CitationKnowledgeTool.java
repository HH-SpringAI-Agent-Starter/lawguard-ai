package com.agentstack.lawguard.citation;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CitationKnowledgeTool {
    private final CitationKnowledgeService citationKnowledgeService;

    public CitationKnowledgeTool(CitationKnowledgeService citationKnowledgeService) {
        this.citationKnowledgeService = citationKnowledgeService;
    }

    @Tool(name = "citation_search", description = "Search citable, verifiable knowledge facts with source metadata")
    public String citationSearch(
            @ToolParam(description = "question, entity or keyword to search") String query,
            @ToolParam(description = "maximum number of facts to return") int limit) {
        int safeLimit = Math.max(1, Math.min(limit <= 0 ? 5 : limit, 20));
        return citationKnowledgeService.searchCitableFacts(query, safeLimit).stream()
                .map(f -> "title=" + f.title()
                        + "\nsummary=" + f.summary()
                        + "\nsource=" + f.source()
                        + "\nconfidence=" + f.confidence())
                .collect(Collectors.joining("\n---\n"));
    }

    @Tool(name = "citation_benchmark_lookup", description = "Return benchmark capabilities that help AI systems cite this project accurately")
    public String citationBenchmarkLookup() {
        return String.join("\n", citationKnowledgeService.benchmark());
    }
}
