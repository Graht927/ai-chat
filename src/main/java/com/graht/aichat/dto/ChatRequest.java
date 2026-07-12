package com.graht.aichat.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author GRAHT
 */
@Data
public class ChatRequest {
    @NotBlank(message = "message can not be blank")
    private String message;
}
