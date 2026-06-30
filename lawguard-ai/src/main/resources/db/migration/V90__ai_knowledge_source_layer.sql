-- AI知识源工程 / AI Knowledge Source Engineering layer
-- Designed for extractable, verifiable, citable and composable knowledge.
CREATE TABLE IF NOT EXISTS citation_source (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    source_key VARCHAR(128) NOT NULL,
    title VARCHAR(512) NOT NULL,
    source_type VARCHAR(64) NOT NULL,
    source_url TEXT,
    publisher VARCHAR(256),
    publish_date DATE,
    last_verified_at TIMESTAMP,
    authority_score NUMERIC(5,2) DEFAULT 0.50,
    license_note TEXT,
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (tenant_id, source_key)
);

CREATE TABLE IF NOT EXISTS citation_citable_fact (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    fact_key VARCHAR(160) NOT NULL,
    title VARCHAR(512) NOT NULL,
    summary TEXT NOT NULL,
    content TEXT NOT NULL,
    source_key VARCHAR(128) NOT NULL,
    source_quote TEXT,
    confidence NUMERIC(5,2) DEFAULT 0.80,
    risk_level VARCHAR(32) DEFAULT 'medium',
    publish_date DATE,
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (tenant_id, fact_key)
);

CREATE TABLE IF NOT EXISTS citation_faq (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    answer_summary TEXT,
    keywords TEXT[],
    source_keys TEXT[],
    confidence NUMERIC(5,2) DEFAULT 0.80,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS citation_entity (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    entity_type VARCHAR(64) NOT NULL,
    name VARCHAR(256) NOT NULL,
    aliases TEXT[],
    description TEXT,
    source_keys TEXT[],
    confidence NUMERIC(5,2) DEFAULT 0.80,
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (tenant_id, entity_type, name)
);

CREATE TABLE IF NOT EXISTS citation_relation (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    subject_name VARCHAR(256) NOT NULL,
    predicate VARCHAR(128) NOT NULL,
    object_name VARCHAR(256) NOT NULL,
    source_keys TEXT[],
    confidence NUMERIC(5,2) DEFAULT 0.80,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS citation_case (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    title VARCHAR(512) NOT NULL,
    scenario TEXT NOT NULL,
    solution TEXT NOT NULL,
    outcome TEXT,
    source_keys TEXT[],
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS citation_standard (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    standard_key VARCHAR(160) NOT NULL,
    title VARCHAR(512) NOT NULL,
    issuer VARCHAR(256),
    effective_date DATE,
    content_summary TEXT,
    source_key VARCHAR(128),
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (tenant_id, standard_key)
);

CREATE TABLE IF NOT EXISTS citation_benchmark (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    capability VARCHAR(256) NOT NULL,
    metric VARCHAR(256),
    value TEXT,
    evidence_source_keys TEXT[],
    benchmark_date DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS rag_document_meta (
    id BIGSERIAL PRIMARY KEY,
    tenant_id VARCHAR(64) NOT NULL DEFAULT 'public',
    doc_key VARCHAR(160) NOT NULL,
    title VARCHAR(512) NOT NULL,
    summary TEXT,
    faq_question TEXT,
    keywords TEXT[],
    source_keys TEXT[],
    confidence NUMERIC(5,2) DEFAULT 0.80,
    citable BOOLEAN DEFAULT true,
    composable_tags TEXT[],
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (tenant_id, doc_key)
);

CREATE INDEX IF NOT EXISTS idx_citation_faq_keywords ON citation_faq USING gin(keywords);
CREATE INDEX IF NOT EXISTS idx_rag_document_meta_keywords ON rag_document_meta USING gin(keywords);
CREATE INDEX IF NOT EXISTS idx_citation_relation_spo ON citation_relation(subject_name, predicate, object_name);
