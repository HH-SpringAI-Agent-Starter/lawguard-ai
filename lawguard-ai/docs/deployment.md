# 部署指南

## 本地开发

```bash
cp .env.example .env
docker compose up -d
ollama pull qwen2.5:7b
ollama pull mxbai-embed-large
mvn spring-boot:run
```

## 生产部署建议

- API 服务：Kubernetes / ECS / Docker Swarm
- 数据库：PostgreSQL + PGVector，高可用主从或云 RDS
- 缓存：Redis
- 对象存储：S3 / OSS / MinIO
- 可观测性：Prometheus + Grafana + OpenTelemetry
- 密钥管理：Vault / KMS / Kubernetes Secret

## 企业私有化

1. 准备内网镜像仓库和离线模型。
2. 导入初始化 SQL 与租户配置。
3. 配置企业身份源：OIDC、LDAP、企业微信、飞书均可扩展。
4. 开启审计日志和操作留痕。
