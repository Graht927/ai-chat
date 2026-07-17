package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.backoff.BackoffStrategy;
import com.graht.aichat.ai.backoff.BackoffStrategyFactory;
import com.graht.aichat.ai.retry.RetryConfig;
import com.graht.aichat.ai.retry.RetryContext;
import com.graht.aichat.ai.retry.RetryPolicyType;
import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DefaultRetryPolicy implements RetryPolicy {
    private final RetryConfigResolver resolver;
    private final BackoffStrategyFactory backoffStrategyFactory;

    public DefaultRetryPolicy(RetryConfigResolver resolver, BackoffStrategyFactory backoffStrategyFactory) {
        this.resolver = resolver;
        this.backoffStrategyFactory = backoffStrategyFactory;
    }

    @Override
    public boolean canRetry(RetryContext context) {
        RetryConfig retryConfig = resolver.resolve(type());
        return context.getRetryCount() < retryConfig.getMaxAttempts();
    }

    @Override
    public RetryPolicyType type() {
        return RetryPolicyType.DEFAULT;
    }

    @Override
    public RetryConfig config() {
        return resolver.resolve(type());
    }

    @Override
    public BackoffStrategy backoffStrategy() {
        return backoffStrategyFactory.getBackoffStrategy(config().getBackoff().getType());
    }

}
