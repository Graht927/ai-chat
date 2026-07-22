package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.config.RetryConfigProperties;
import com.graht.aichat.exception.AIException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class RetryConfigRegistry {
    private final Map<RetryPolicyType,RetryConfig> policies;
    public RetryConfigRegistry(RetryConfigProperties policies) {
        this.policies =
                Map.copyOf(
                        policies.getPolicies()
                );
    }
    public RetryConfig getPolicy(RetryPolicyType type) {
        RetryConfig retryConfig = policies.get(type);
        if (retryConfig == null) {
            throw new AIException(AIErrorCode.RETRY_CONFIG_NOT_FOUND);
        }
        return retryConfig;
    }
}
