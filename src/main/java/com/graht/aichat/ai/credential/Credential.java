package com.graht.aichat.ai.credential;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credential {

    private String apiKey;

    private String baseUrl;

}