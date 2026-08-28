package com.agentstack.lawguard.rag;

import com.agentstack.lawguard.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeBaseService {

    private static final Logger log = LoggerFactory.getLogger(KnowledgeBaseService.class);
    private static final int DEFAULT_TOP_K = 5;

    private final VectorStore vectorStore;

    public KnowledgeBaseService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, int topK) {
        return search(query, null, topK);
    }

    public List<Document> search(String query, String sourceType, int topK) {
        String tenantId = TenantContext.getTenantId();
        String filter = "tenant_id == '" + escape(tenantId) + "'";
        if (sourceType != null && !sourceType.isBlank()) {
            filter = filter + " && source_type == '" + escape(sourceType) + "'";
        }
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK > 0 ? topK : DEFAULT_TOP_K)
                .similarityThreshold(0.5)
                .filterExpression(filter)
                .build();
        List<Document> results = vectorStore.similaritySearch(request);
        log.info("KB search tenant={} type={} query={} hits={}", tenantId, sourceType, query, results.size());
        return results;
    }

    public void ingest(String content, String sourceType, String sourceUri, String title) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("tenant_id", TenantContext.getTenantId());
        metadata.put("source_type", sourceType);
        metadata.put("source_uri", sourceUri);
        metadata.put("title", title);
        vectorStore.add(List.of(new Document(content, metadata)));
        log.info("KB ingest tenant={} type={} uri={}", metadata.get("tenant_id"), sourceType, sourceUri);
    }

    private static String escape(String value) {
        return value == null ? "" : value.replace("'", "''");
    }
}