package com.graht.aichat.ai.core.domain;

import com.graht.aichat.ai.core.model.AIProvider;
import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */

@Data
@Builder
public class AIResponse {
    private String answer;
    private AIProvider provider;
    private String model;
    private String modelVersion;
    private String status;
    private TokenUsage tokenUsage;

}
