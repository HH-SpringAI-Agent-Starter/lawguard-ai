# ============================================================
# LawGuard AI Community — 多阶段构建
# 用法: docker build -t lawguard-ai:0.3.1 .
# 依赖: 先 docker compose up -d postgres redis minio 启动基础设施
# ============================================================

# ---- Stage 1: 编译 ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- Stage 2: 运行时 ----
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS="-Xms256m -Xmx512m"
COPY --from=build /build/target/lawguard-ai-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]