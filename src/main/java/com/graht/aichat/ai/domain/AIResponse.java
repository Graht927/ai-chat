package com.graht.aichat.ai.domain;

import lombok.Data;

/**
 * @author GRAHT
 */

@Data
public class AIResponse {
    private String answer;
    private String modelName;
    private String modelType;
    private String modelVersion;
    private TokenUsage tokenUsage;
    private String requestId;
    private String status;

}
