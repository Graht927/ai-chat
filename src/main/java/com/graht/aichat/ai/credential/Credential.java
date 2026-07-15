package com.graht.aichat.ai.credential;

import com.graht.aichat.ai.retry.RetryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credential {

    private String apiKey;

    private String baseUrl;

    private RetryType retryType;

}