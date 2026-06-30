# LawGuard AI Community

        法律合规检索与合同审查 Agent：基于 **Spring AI 2.0 + Agent Tool Calling + PGVector RAG + Ollama** 的法律/合规助手项目模板。

        > 免责声明：本项目仅用于法律检索、合同审查辅助和技术演示，不构成正式法律意见。所有结论必须由执业律师或合规负责人复核。

        ## 场景定位

        在法规、合同模板、历史案例库中进行 RAG 检索，辅助律师、法务和合规团队进行合同审查、条款风险识别和案例研究。

        ## 版本定位：开源版


- 单租户或轻量租户 Header 演示
- 本地 Ollama 模型，适合开发和 Demo
- 基础 RAG 知识库、基础工具调用、REST API
- Apache-2.0 友好的开源项目结构
- 可作为 Open Core 的免费获客漏斗

        ## 核心能力

        - Spring AI `ChatClient` Agent 编排
        - `@Tool` 工具调用
        - PGVector 私有知识库 RAG
        - Ollama 本地模型默认配置
        - Docker Compose 一键启动基础设施
        - Flyway 数据库初始化
        - Prometheus / Actuator 可观测性

        ## 工具清单

        - `regulation_rag_search`
- `case_law_search`
- `contract_clause_extract`
- `clause_risk_check`
- `citation_builder`
- `compliance_disclaimer`

        ## 连接器方向

        - 法规库
- 裁判文书/案例库
- 合同库/OSS
- 企业合规制度库

        ## API

        | Method | Path | Description |
        |---|---|---|
        | POST | `/api/agent/ask` | 法律/合规研究问答 |
| POST | `/api/contracts/review` | 合同风险审查 |
| POST | `/api/compliance/check` | 合规话术与规则检查 |
        | POST | `/api/kb/sync` | 同步知识库 |
        | GET | `/api/kb/search?q=` | 检索知识库 |

        ## 本地运行

        ```bash
        cp .env.example .env
        docker compose up -d
        ollama pull qwen2.5:7b
        ollama pull mxbai-embed-large
        mvn spring-boot:run
        ```

        ## 示例调用

        ```bash
        curl -s -X POST http://localhost:8080/api/agent/ask \
          -H 'Content-Type: application/json' \
          -H 'X-Tenant-Id: demo' \
          -d '{
            "question": "请审查这段竞业限制条款，指出对公司和员工分别有哪些风险。",
            "userId": "u_1001",
            "sessionId": "s_demo"
          }' | jq
        ```

        ## 目录结构

        ```text
        src/main/java/.../agent        Agent 编排
        src/main/java/.../tools        工具调用
        src/main/java/.../rag          RAG 服务
        src/main/java/.../tenant       多租户上下文
        src/main/resources/kb          示例知识库
        src/main/resources/db          Flyway 初始化 SQL
        docs/                          架构、API、部署、定价、演示脚本
        ```

        ## GitHub 上传

        ```bash
        git init
        git add .
        git commit -m "Initial commit: LawGuard AI Community"
        gh repo create lawguard-ai --public --source=. --remote=origin --push
        ```
