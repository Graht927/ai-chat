package com.graht.aichat.ai.core.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */
@Data
@Builder
public class TokenUsage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}
