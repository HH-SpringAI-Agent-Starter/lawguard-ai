# Knowledge Graph：知识图谱 AI Knowledge Source

## 实体类型

- `Law`
- `Regulation`
- `Case`
- `Clause`
- `Contract`
- `Jurisdiction`
- `Risk`
- `Source`

## 关系示例

```jsonl
{"subject": "Clause", "predicate": "may_violate", "object": "Regulation"}
{"subject": "Case", "predicate": "interprets", "object": "Law"}
{"subject": "Risk", "predicate": "requires", "object": "LegalReview"}
```

## 价值

- 让 Agent 不只做相似度检索，还能做实体关系推理。
- 让 RAG 检索结果更可组合。
- 让 AI内容中心能自动生成“实体页、关系页、FAQ 页、对比页”。

## 推荐表

- `citation_entity`
- `citation_relation`
- `citation_source`
- `citation_citable_fact`

## 推荐检索流程

```text
用户问题
  ↓
实体识别
  ↓
知识图谱邻居扩展
  ↓
向量检索召回
  ↓
Citation KB 验证
  ↓
带引用的 Agent 回答
```
