package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffStrategy;
import com.graht.aichat.infrastructure.retry.BackoffStrategyFactory;
import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryContext;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
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
