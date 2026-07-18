package com.graht.aichat.ai.provider.deepseek;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekResponse {

    private String id;

    private String model;

    private List<Choice> choices;

    private Usage usage;


    @Data
    public static class Choice {

        private Message message;

    }


    @Data
    public static class Message {

        private String role;

        private String content;

        private String reasoning_content;

    }


    @Data
    public static class Usage {

        private Integer total_tokens;

        private Integer prompt_tokens;

        private Integer completion_tokens;

    }

}