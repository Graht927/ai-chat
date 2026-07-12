package com.graht.aichat.ai.dto;

import com.graht.aichat.ai.model.ModelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author GRAHT
 */
@Data
public class AIRequest {
    @NotNull(message = "modelType cannot be null")
    private ModelType modelType;
    @NotBlank(message = "prompt cannot be blank")
    private String prompt;
    private String requestId;
    private Double temperature;
    private Integer maxTokens;
}
