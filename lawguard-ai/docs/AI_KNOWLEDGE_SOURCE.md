# AI知识源工程：AI 知识源工程

## 核心判断

传统 AI Knowledge Source 如果只是 `SEO 思维 + AI 包装`，很难稳定被生成式搜索引用。本项目采用 AI Knowledge Source Engineering，把项目本身建设为可抽取、可验证、可引用、可组合的知识源。

## 四个设计原则

| 原则 | 含义 | 项目实现 |
|---|---|---|
| Extractable | 大模型容易抽取结构化事实 | FAQ、JSONL、表结构、标题摘要 |
| Verifiable | 人和 AI 能追溯来源 | Citation KB、source_url、publish_date |
| Citable | 答案可作为引用来源 | source_key、quote、confidence |
| Composable | 知识可组合成多跳推理 | Knowledge Graph、关系表、tags |

## 内容资产路径

```text
项目代码
  ↓
README / API / FAQ / Benchmark
  ↓
Citation KB / Knowledge Graph / RAG Metadata
  ↓
AI 可引用知识源
  ↓
ChatGPT / Perplexity / Gemini / Claude 可能引用
```

## AI知识源工程 最小闭环

1. 重构 README，让项目“是什么、解决什么问题、适用谁、如何部署、API 如何用”一眼可抽取。
2. 维护 AI FAQ 库，每个问题都用简短、确定、可引用的答案。
3. 建立 Citation KB，每条关键事实绑定来源、日期、置信度和风险等级。
4. 建立知识图谱，表达核心实体和实体关系。
5. 建立 Benchmark，公开项目能力、限制和对比指标。
6. 把 RAG 元数据从纯 `content` 升级为 `title + summary + faq + keywords + source + confidence`。

## 生产要求

- 高风险行业事实必须人工审核。
- 外部来源 URL 必须定期校验。
- 过期标准、旧版政策、失效链接不得进入高置信答案。
- Agent 输出应显示引用来源和不确定性。
