package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.backoff.BackoffStrategy;
import com.graht.aichat.ai.backoff.ExponentialBackoffStrategy;
import com.graht.aichat.ai.retry.RetryType;
import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author GRAHT
 */
@Component
public class ExponentialRetryPolicy implements RetryPolicy {
    private final AIRetryProperties properties;
    private final BackoffStrategy backoffStrategy;
    public  ExponentialRetryPolicy(AIRetryProperties properties, ExponentialBackoffStrategy exponentialBackoffStrategy) {
        this.properties = properties;
        this.backoffStrategy = exponentialBackoffStrategy;
    }

    @Override
    public boolean canRetry(int attempt, Exception e) {
        return attempt < properties.getMaxAttempts();
    }

    @Override
    public long nextBackoff(int attempt) {
        return backoffStrategy.nextBackoffMillis(attempt);
    }

    @Override
    public RetryType type() {
        return RetryType.EXPONENTIAL;
    }
}
