# Changelog

All notable changes to LawGuard AI Community will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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
