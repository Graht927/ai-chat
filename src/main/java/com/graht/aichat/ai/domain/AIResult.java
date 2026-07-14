package com.graht.aichat.ai.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */
@Data
@Builder
public class AIResult {
    private String answer;
    private String requestId;
    private TokenUsage tokenUsage;

}
