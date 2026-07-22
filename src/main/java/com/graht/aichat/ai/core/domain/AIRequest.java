package com.graht.aichat.ai.core.domain;

import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author GRAHT
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AIRequest {
    @NotBlank(message = "prompt cannot be blank")
    private String message;
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
