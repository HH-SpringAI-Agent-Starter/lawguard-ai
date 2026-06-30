package com.agentstack.lawguard.citation;

import java.util.List;

public record CitableFact(
        String title,
        String summary,
        String content,
        String source,
        String sourceType,
        String confidence,
        List<String> keywords
) {
}
