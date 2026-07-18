package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class RetryPolicyFactory {
    private final RetryPolicyRegistry retryPolicyRegistry;

    public RetryPolicyFactory(RetryPolicyRegistry retryPolicyRegistry) {
        this.retryPolicyRegistry = retryPolicyRegistry;
    }
    public RetryPolicy getRetryPolicy(RetryPolicyType type) {
        return retryPolicyRegistry.getPolicy(type);
    }
}
