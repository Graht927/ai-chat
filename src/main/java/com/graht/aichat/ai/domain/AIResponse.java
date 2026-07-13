package com.graht.aichat.ai.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */

@Data
@Builder
public class AIResponse {
    private String answer;
    private String provider;
    private String model;
    private String modelVersion;
    private TokenUsage tokenUsage;
    private String requestId;
    private String status;

}
