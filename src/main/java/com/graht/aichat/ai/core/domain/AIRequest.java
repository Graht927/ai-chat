package com.graht.aichat.ai.core.domain;

import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.common.EndpointType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * @author GRAHT
 */
@Data
@Builder
public class AIRequest {
    @NotBlank(message = "prompt cannot be blank")
    private String prompt;
    private String requestId;
    private Double temperature;
    private AIProvider provider;
    private AICapability aiCapability;
    private Integer maxTokens;
    private Long userId;
    private String model;

    public ProviderCapabilityKey supportType(){
        return ProviderCapabilityKey.of(provider, aiCapability);
    }
}
