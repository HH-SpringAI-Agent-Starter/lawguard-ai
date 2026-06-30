# 架构设计

```text
Client / Web Console / API Consumer
        |
Spring Boot API Gateway
        |
Agent Orchestrator --- Tool Registry --- External Systems
        |
RAG Service --- EmbeddingModel --- PGVector
        |
PostgreSQL / Redis / Object Storage
```

核心思路：

1. 用户问题进入 Agent Orchestrator。
2. Agent 根据 System Prompt、会话上下文、工具描述判断是否调用工具。
3. RAG Service 将私有知识库分块、向量化、检索，并把检索片段作为上下文。
4. 对外部系统的读操作优先自动执行，写操作默认生成草稿或进入审批。
5. 企业版增加多租户、RBAC、审计、配额、监控和私有化部署。
