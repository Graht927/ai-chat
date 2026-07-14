package com.graht.aichat.ai.dto;

import com.graht.aichat.ai.credential.CredentialContext;
import com.graht.aichat.ai.model.ModelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private ModelType modelType;
    private Integer maxTokens;
    private CredentialContext context;
    private Long userId;
    private String model;
}
