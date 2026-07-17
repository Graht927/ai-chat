package com.graht.aichat.config;

import com.graht.aichat.ai.retry.RetryConfig;
import com.graht.aichat.ai.retry.RetryPolicyType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */
@ConfigurationProperties(prefix = "ai.retry.config")
@Component
public class RetryConfigProperties {
    /**
     * 不同策略的配置
     */
    private Map<RetryPolicyType, RetryConfig> policies;

    public Map<RetryPolicyType, RetryConfig> getTypeRetryConfig() {
        return policies;
    }
}
