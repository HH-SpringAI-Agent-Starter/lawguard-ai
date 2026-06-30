# RAG Metadata 规范

普通 RAG 只存 `content`，AI知识源工程 RAG 必须存可抽取、可验证、可引用的结构化元数据。

## 推荐结构

```json
{{
  "doc_key": "domain-topic-001",
  "title": "文档标题",
  "summary": "一句话摘要",
  "content": "正文片段",
  "faq_question": "这个片段能回答什么问题？",
  "keywords": ["关键词1", "关键词2"],
  "source_keys": ["source-key"],
  "confidence": 0.88,
  "citable": true,
  "composable_tags": ["entity", "case", "standard"]
}}
```

## 检索排序建议

```text
最终分数 = 向量相似度 × 0.55
        + 来源权威分 × 0.20
        + 新鲜度分 × 0.10
        + FAQ 命中分 × 0.10
        + 被引用频率分 × 0.05
```

## 输出要求

Agent 回答必须尽可能包含：

- 引用来源
- 置信度
- 不确定性
- 下一步建议
- 是否需要人工复核
