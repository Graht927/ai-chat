package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.retry.RetryConfig;
import com.graht.aichat.ai.retry.RetryConfigRegistry;
import com.graht.aichat.ai.retry.RetryPolicyType;
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
        return  retryConfigRegistry.getConfig(retryPolicyType);
    }
}
