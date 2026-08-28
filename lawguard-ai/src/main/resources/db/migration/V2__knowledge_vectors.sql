-- RAG 知识库向量表（Spring AI PgVectorStore, dimensions=1024, 与 Ollama mxbai-embed-large 对齐）
CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS knowledge_vectors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT NOT NULL,
    metadata JSONB NOT NULL DEFAULT '{}'::jsonb,
    embedding vector(1024),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- 多租户元数据过滤索引（tenant_id / source_type 用于 JSONB metadata 过滤）
CREATE INDEX IF NOT EXISTS idx_knowledge_vectors_tenant
    ON knowledge_vectors USING GIN ((metadata -> 'tenant_id'));

CREATE INDEX IF NOT EXISTS idx_knowledge_vectors_source_type
    ON knowledge_vectors USING GIN ((metadata -> 'source_type'));