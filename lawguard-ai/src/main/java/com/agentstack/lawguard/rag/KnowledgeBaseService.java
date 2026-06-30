package com.agentstack.lawguard.rag;

import com.agentstack.lawguard.tenant.TenantContext;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KnowledgeBaseService {
    public List<String> search(String query) {
        // TODO: Replace this stub with Spring AI VectorStore.similaritySearch()
        // and include tenant_id metadata filters for enterprise deployment.
        return List.of(
                "tenant=" + TenantContext.getTenantId() + "; matched policy chunk for query: " + query,
                "kb://" + TenantContext.getTenantId() + "/sample-doc/1"
        );
    }
}
