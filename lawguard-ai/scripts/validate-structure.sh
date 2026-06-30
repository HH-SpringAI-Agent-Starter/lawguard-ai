#!/usr/bin/env bash
set -euo pipefail
[[ -f pom.xml ]] || { echo "missing pom.xml"; exit 1; }
[[ -f README.md ]] || { echo "missing README.md"; exit 1; }
[[ -d src/main/java ]] || { echo "missing src/main/java"; exit 1; }
[[ -d docs ]] || { echo "missing docs"; exit 1; }
[[ -d src/main/java ]] && find src/main/java -name "*.java" | grep -q . || { echo "no Java files"; exit 1; }
[[ -d src/main/resources/citation-kb ]] || { echo "missing citation-kb resources"; exit 1; }
echo "structure ok"
