package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.retry.RetryType;
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
    public RetryPolicy getRetryPolicy(RetryType type) {
        return retryPolicyRegistry.getPolicy(type);
    }
}
