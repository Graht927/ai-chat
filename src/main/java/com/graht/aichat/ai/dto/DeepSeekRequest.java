package com.graht.aichat.ai.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeepSeekRequest {

    private String model;

    private List<Message> messages;

    private Boolean stream;


    @Data
    @Builder
    public static class Message {

        private String role;

        private String content;

    }

}