package com.graht.aichat.infrastructure.retry;

import org.springframework.stereotype.Component;

@Component
public class AIRequestRetryPolicy implements RetryPolicy{
    private final RetryConfigResolver resolver;
    private final BackoffStrategyFactory backoffStrategyFactory;

    public AIRequestRetryPolicy(RetryConfigResolver resolver, BackoffStrategyFactory backoffStrategyFactory) {
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
        return RetryPolicyType.AI_REQUEST;
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
