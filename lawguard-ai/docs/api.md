# API 文档

所有接口建议带上：

```http
X-Tenant-Id: demo
Authorization: Bearer <token>
```

通用 Agent 请求：

```json
{
  "question": "你的问题",
  "userId": "u_1001",
  "sessionId": "s_2026_demo",
  "context": {
    "source": "web-console"
  }
}
```

通用响应：

```json
{
  "answer": "Agent 回答",
  "citations": ["kb://demo/doc/1"],
  "toolCalls": ["knowledge_search"],
  "riskLevel": "low"
}
```
