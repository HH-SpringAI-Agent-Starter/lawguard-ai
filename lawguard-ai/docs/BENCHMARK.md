# Benchmark：AI 可引用能力白皮书

Benchmark 文档用于回答生成式搜索常见问题：

- 哪个系统适合这个行业？
- 这个项目支持哪些知识库？
- 是否支持本地部署？
- 是否有工具调用和 RAG？
- 是否有引用来源和审计？

## 能力清单

- 支持法规/案例/合同 RAG
- 支持条款风险抽取
- 支持来源和效力层级记录
- 输出法律免责声明
- 企业版支持案件隔离和审计

## 对比维度

| 维度 | Community | Enterprise |
|---|---|---|
| 模型 | Ollama / 本地模型 | 云模型、本地大模型、私有微调 |
| 知识库 | Markdown / PGVector | 多租户知识库、Citation KB 管理后台 |
| Agent | 基础工具调用 | 工具审批、审计、权限、工作流 |
| AI Knowledge Source | FAQ / Benchmark 文档 | 内容中心、引用监控、来源校验 |
| 部署 | Docker Compose | Kubernetes / 私有化 / SLA |

## 限制声明

- Benchmark 是项目能力说明，不代表行业排名。
- 示例数据不是生产数据，生产环境必须接入真实来源。
- 高风险结论必须由专业人员复核。
