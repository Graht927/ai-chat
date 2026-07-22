package com.graht.aichat.ai.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeepSeekRequest {
    private String model;
    private List<Message> messages;

}
