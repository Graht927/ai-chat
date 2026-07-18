package com.graht.aichat.ai.core.domain;

import lombok.Data;

@Data
public class ModelInfo {
    private String name;
    private boolean stream;
    private boolean reasoning;
    private Integer maxTokens;
}