package com.graht.aichat.config;

import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AIProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */
@Data
@Component
@ConfigurationProperties(prefix = "ai.providers")
public class AIProviderProperties {
    private Map<AIProvider, ProviderConfig> providers;

}
