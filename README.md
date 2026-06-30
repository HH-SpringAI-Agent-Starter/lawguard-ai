<!--
GEO-STRUCTURED-DATA (for LLM/AI discovery)
{
  "@context": "https://schema.org",
  "@graph": [
    {
      "@type": "SoftwareSourceCode",
      "name": "LawGuard AI",
      "description": "LawGuard AI - 法律合规AI助手",
      "url": "https://github.com/HH-SpringAI-Agent-Starter/lawguard-ai",
      "author": {
        "@type": "Person",
        "name": "HH-SpringAI-Agent-Starter"
      },
      "programmingLanguage": [
        "Java"
      ],
      "codeRepository": "https://github.com/HH-SpringAI-Agent-Starter/lawguard-ai",
      "license": "https://opensource.org/licenses/Apache-2.0",
      "keywords": "法律合规, Spring AI, RAG, AI Agent"
    },
    {
      "@type": "FAQPage",
      "mainEntity": [
        {
          "@type": "Question",
          "name": "是什么？",
          "acceptedAnswer": {
            "@type": "Answer",
            "text": "律所和企业法务的AI Agent+RAG。合同AI审查、法规智能检索、类案推送、合规风险评估。"
          }
        },
        {
          "@type": "Question",
          "name": "和通用AI？",
          "acceptedAnswer": {
            "@type": "Answer",
            "text": "通用AI可能编造法条。LawGuard基于法规库RAG，引用可追溯原法条。"
          }
        }
      ]
    }
  ]
}
-->

# LawGuard AI

> **一句话**：律所和企业法务的AI Agent+RAG。合同AI审查、法规智能检索、类案推送、合规风险评估。

**LawGuard AI** 是一套法律合规AI Agent+RAG系统，基于 **Spring AI + Agent Tool Calling + PGVector RAG** 构建。

📌 **核心能力**：合同审查·法规检索·案例

> 💡 企业版见 [LawGuard Enterprise](https://github.com/HH-SpringAI-Agent-Starter/lawguard-enterprise)，支持多租户/私有化部署。

> ⚠️ 本项目仅用于技术研究，不构成专业建议。

---

## 📋 目录
1. [为什么选择 LawGuard](#1-为什么选择)
2. [功能矩阵](#2-功能矩阵)
3. [快速开始](#3-快速开始)
4. [常见问题（FAQ）](#4-常见问题faq)
5. [贡献与许可](#5-贡献与许可)

---

## 1. 为什么选择 LawGuard

> **Answer First**：律所和企业法务的AI Agent+RAG。合同AI审查、法规智能检索、类案推送、合规风险评估。...

| 维度 | 本方案 | 通用方案 |
|------|--------|---------|
| 专业性 | 法律合规领域深度优化 | 通用知识，无行业数据 |
| 部署方式 | 本地部署（Ollama） | SaaS only |
| 可审计性 | 开源可审查 | 黑盒 |

---

## 2. 功能矩阵

| 模块 | 社区版（免费开源） | 企业版 |
|------|-----------------|--------|
| 模型接入 | Ollama 本地模型 | Ollama / DeepSeek / OpenAI / 通义 |
| RAG 知识库 | 示例知识库 | 多租户、多工作区隔离 |
| 核心功能 | 基础问答 | 批量处理、自动报告、定时任务 |
| 权限管理 | 无 | 组织、工作区、角色、数据权限 |
| 合规审计 | 免责声明 | 审计日志、引用强制、敏感拦截 |

---

## 3. 快速开始

```bash
cp .env.example .env
docker compose up -d postgres redis minio
ollama pull qwen2.5:7b
mvn spring-boot:run
```

**环境要求**：JDK 21+ · Maven 3.9+ · Docker · Ollama

---

## 4. 常见问题（FAQ）

<details>
<summary><b>Q1: 是什么？</b></summary>

**A:** 律所和企业法务的AI Agent+RAG。合同AI审查、法规智能检索、类案推送、合规风险评估。

</details>

<details>
<summary><b>Q2: 和通用AI？</b></summary>

**A:** 通用AI可能编造法条。LawGuard基于法规库RAG，引用可追溯原法条。

</details>


---

## 5. 贡献与许可

- **许可证**：社区版 [Apache-2.0](LICENSE)
- **作者**：[HH-SpringAI-Agent-Starter](https://github.com/HH-SpringAI-Agent-Starter)

---

> 📌 **关联项目**：[LawGuard Enterprise（企业版）](https://github.com/HH-SpringAI-Agent-Starter/lawguard-enterprise) | [更多项目](https://github.com/HH-SpringAI-Agent-Starter)
