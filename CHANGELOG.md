# Changelog

All notable changes to LawGuard AI Community will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.3.0] - 2026-08-28

### Added
- `VectorStoreConfig`: 显式 `PgVectorStore` Bean（`knowledge_vectors` 表，1024 维，COSINE_DISTANCE），与 Ollama mxbai-embed-large 对齐
- `V2__knowledge_vectors.sql`: 向量表迁移脚本 + tenant_id / source_type GIN 索引
- `ScenarioController`: 补齐 README 已声明但缺失的 `/api/contracts/review` 与 `/api/compliance/check` 端点

### Changed
- `KnowledgeBaseService`: 由 TODO 桩升级为真实 PGVector 相似度检索，支持租户元数据过滤（`tenant_id == ...`）与来源类型过滤，新增 `ingest()` 向量入库
- `DomainTools`: 六个工具由 demo stub 升级为真实 RAG 检索——`regulation_rag_search` / `case_law_search` / `contract_clause_extract` 按 source_type 检索并附带来源引用，`clause_risk_check` 叠加高风险关键词规则，`citation_builder` 输出可追溯引用列表，`compliance_disclaimer` 返回免责声明
- `KnowledgeBaseController`: `/api/kb/sync` 实现文档入库，`/api/kb/search` 支持 type 过滤与 topK

## [0.2.1] - 2026-08-07

### Fixed
- Root `README.md` rewritten: fixed UTF-8 double-encoding mojibake in Chinese content, added Mermaid architecture diagram and tech stack table
- Root `requirements.md` rewritten: UTF-8 clean, full functional specs (contract review / regulation search / case law / compliance check), Mermaid architecture, data flow, API list, roadmap
- Root `CONTRIBUTING.md` rewritten: UTF-8 clean, PR workflow, code style, tool contribution checklist

### Added
- Root `LICENSE` — full Apache-2.0 license text (was only present under `lawguard-ai/`)
- Root `.gitignore` upgraded from 40B stub to comprehensive Java/Maven/IDE/Docker/Secrets rules

## [0.2.0] - 2026-07-03

### Added
- Comprehensive requirements document (`requirements.md`) covering all functional modules, architecture design, and data flow
- Root-level `CONTRIBUTING.md` with PR workflow, code style guide, and tool contribution checklist
- Enhanced `.gitignore` with Java/Maven/IDEA/Gradle/Docker patterns
- Architecture diagram in root `README.md`

### Changed
- Improved root `README.md` completeness: MIT License, tech stack, quick start, architecture diagram all present

## [0.1.0] - 2026-06-15

### Added
- Initial community edition release
- Spring AI 2.0 Agent orchestration with `ChatClient`
- Six core tools: `regulation_rag_search`, `case_law_search`, `contract_clause_extract`, `clause_risk_check`, `citation_builder`, `compliance_disclaimer`
- PGVector RAG knowledge base integration
- Ollama local model support (qwen2.5:7b + mxbai-embed-large)
- Docker Compose one-click infrastructure
- Flyway database migration
- Prometheus / Actuator observability
- Multi-tenant context support (header-based demo)
- REST API: `/api/agent/ask`, `/api/contracts/review`, `/api/compliance/check`, `/api/kb/sync`, `/api/kb/search`
- Geo-structured data embedding for LLM/AI discovery