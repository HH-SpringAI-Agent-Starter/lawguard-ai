# Contributing to LawGuard AI

感谢你对 LawGuard AI 的关注！我们欢迎任何形式的贡献。

## 📋 目录
1. [行为准则](#行为准则)
2. [如何贡献](#如何贡献)
3. [开发环境搭建](#开发环境搭建)
4. [PR 工作流](#pr-工作流)
5. [代码规范](#代码规范)
6. [工具贡献清单](#工具贡献清单)

---

## 行为准则

本项目遵循 [Contributor Covenant](https://www.contributor-covenant.org/version/2/1/code_of_conduct/) 行为准则。请保持专业、尊重。

---

## 如何贡献

| 贡献方式 | 适合人群 | 说明 |
|----------|----------|------|
| 🐛 提交 Issue | 所有人 | 报告 Bug、提出功能建议 |
| 📝 改进文档 | 法律从业者 | 完善 API 文档、补充法律场景 |
| 🔧 修复 Bug | 开发者 | 认领 Good First Issue |
| ⚡ 新增工具 | 开发者 | 按工具规范贡献新 Tool |
| 🗂️ 贡献知识库 | 法律从业者 | 贡献脱敏的法规/案例数据 |

---

## 开发环境搭建

```bash
# 前置要求
# - JDK 17+
# - Maven 3.9+
# - Docker + Docker Compose
# - Ollama

# 1. 克隆仓库
git clone https://github.com/HH-SpringAI-Agent-Starter/lawguard-ai.git
cd lawguard-ai/lawguard-ai

# 2. 配置环境
cp .env.example .env

# 3. 启动基础设施
docker compose up -d

# 4. 拉取模型
ollama pull qwen2.5:7b
ollama pull mxbai-embed-large

# 5. 启动应用
mvn spring-boot:run
```

---

## PR 工作流

1. **Fork** 本仓库
2. 创建特性分支：`git checkout -b feat/your-feature-name`
3. 编写代码 + 测试
4. 确保 `mvn verify` 通过
5. 提交 PR，使用模板描述：
   - 场景描述（解决什么法律场景）
   - 输入示例
   - 期望输出
   - 涉及连接器（法规库/案例库/合同库）
   - 是否需要企业版能力
6. 等待 Code Review（至少一位 Maintainer 通过）

---

## 代码规范

### Java 代码风格
- 遵循 Google Java Style Guide
- 类名：PascalCase，方法/变量：camelCase
- 包名：全小写，按 `com.lawguard.<module>` 组织

### 工具开发规范 ⚠️

新增工具必须满足以下要求：

1. **工具描述完整**：`@Tool(description = "清晰的中文描述，含输入输出说明")`
2. **引用必须可追溯**：RAG 检索结果必须包含来源法条/案例编号
3. **安全第一**：外部系统写操作必须先创建草稿或进入审批
4. **补充测试**：每个工具至少 1 个单元测试
5. **文档同步**：更新 `docs/api.md` 对应章节

### 不要做的事情 ❌
- 不要提交 API Key、密码、客户数据
- 不要在代码中硬编码敏感信息
- 不要跳过 TenantContext 直接访问数据
- 不要在工具中直接执行外部系统写操作（必须走审批草稿）

---

## 工具贡献清单

欢迎贡献以下方向的 Tool：

| 优先级 | 工具方向 | 状态 |
|--------|----------|------|
| P0 | 合同要素提取（违约金/解除条件/不可抗力） | 🔴 待贡献 |
| P0 | 法规时效性自动检查 | 🔴 待贡献 |
| P1 | 法律文书自动生成（起诉状/答辩状模板） | 🔴 待贡献 |
| P1 | 知识产权侵权判定辅助 | 🔴 待贡献 |
| P2 | 多语言合同对比 | 🔴 待贡献 |
| P2 | 劳动法专项问答 | 🔴 待贡献 |
| P2 | 数据隐私合规（GDPR/PIPL）检查 | 🔴 待贡献 |

---

## 许可证

贡献的代码默认以本项目相同的 [Apache License 2.0](LICENSE) 授权。
