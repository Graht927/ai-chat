package com.graht.aichat.ai.domain;

import lombok.Data;

/**
 * @author GRAHT
 */
@Data
public class TokenUsage {
    private Long promptTokens;
    private Long completionTokens;
    private Long totalTokens;
}
