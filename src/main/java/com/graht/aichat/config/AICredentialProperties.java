package com.graht.aichat.config;

import com.graht.aichat.ai.credential.Credential;
import com.graht.aichat.ai.model.ModelType;
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
    private Map<String,CredentialConfig> models;
    @Data
    public static class CredentialConfig {
        private String apiKey;
        private String baseUrl;
    }
}
