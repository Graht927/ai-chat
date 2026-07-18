package com.graht.aichat.config;

import com.graht.aichat.ai.model.AIProvider;
import com.graht.aichat.ai.retry.RetryPolicyType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */
@Data
@Component
@ConfigurationProperties(prefix = "ai.credentials")
public class AICredentialProperties {
    private Map<AIProvider,CredentialConfig> models;
    @Data
    public static class CredentialConfig {
        private String apiKey;
        private String baseUrl;
        private RetryPolicyType retryPolicyType = RetryPolicyType.AI_REQUEST;
        private String defaultModel;
    }
}
