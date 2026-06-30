# Citation KB：AI 可引用知识库

Citation KB 专门存放可验证事实，不直接存“模型观点”。

## 数据结构

```json
{
  "fact_key": "unique-fact-id",
  "title": "事实标题",
  "summary": "一句话摘要",
  "content": "完整事实内容",
  "source": "来源名称",
  "source_url": "https://example.com/source",
  "publish_date": "YYYY-MM-DD",
  "confidence": 0.92,
  "risk_level": "medium"
}
```

## 为什么单独建 Citation KB

1. 普通知识库经常混入观点、旧资料和无来源内容。
2. 高风险场景需要回答可追溯。
3. 大模型更容易引用结构稳定、来源明确、答案边界清楚的知识。
4. 未来可统计“被引用频率”，反向优化知识资产。

## 本项目示例来源

| source_key | title | type | note |
|---|---|---|---|
| `regulation-db` | 法规库示例 | legal_source | 法律法规、部门规章、司法解释、行业监管规则。 |

## 生产落地建议

- 每条事实必须绑定 `source_key`。
- 来源要记录发布者、发布日期、生效日期和最后校验时间。
- 答案中要区分：事实、推理、建议、限制。
- 对医疗、法律、金融等场景必须加入人工复核流程。
