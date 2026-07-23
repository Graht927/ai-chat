package com.graht.aichat.ai.core.domain;

import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */

@Data
@Builder
public class AIResponse {
    private String answer;
    private ProviderCapabilityKey providerCapability;
    private String model;
    private String status;
    private AICapability capability;
    private TokenUsage tokenUsage;

}
