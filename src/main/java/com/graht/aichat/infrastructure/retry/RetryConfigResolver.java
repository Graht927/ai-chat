package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryConfigRegistry;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class RetryConfigResolver {
    private final RetryConfigRegistry retryConfigRegistry;
    public RetryConfigResolver(RetryConfigRegistry retryConfigRegistry) {
        this.retryConfigRegistry = retryConfigRegistry;
    }
    public RetryConfig resolve(RetryPolicyType retryPolicyType) {
        return  retryConfigRegistry.getPolicy(retryPolicyType);
    }
}
