# Validation Report

Validation performed in this environment after renaming and optimization.

Result: **18 / 18 projects passed**.

Checks performed:

- Root package folder has no `geo` or `geo2` suffix.
- No project path contains `/geo/`, `geo2`, or `-geo2`.
- JSONL files under `src/main/resources/citation-kb` are valid JSONL.
- Structure check passed with `bash scripts/validate-structure.sh`.
- Java main source syntax check passed with `javac --release 21` plus local API stubs for Spring/Spring AI/Jackson because Maven is not installed in this environment.

Maven full dependency build was not executed here because `/usr/bin/mvn` is unavailable. Run this locally or in GitHub Actions:

```bash
mvn -q -DskipTests package
```

The projects use Java 21, Spring Boot 4.1.0 and Spring AI 2.0.0.
