package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.backoff.BackoffStrategy;
import com.graht.aichat.ai.backoff.FixedBackoffStrategy;
import com.graht.aichat.ai.retry.RetryType;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.config.AIRetryProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author GRAHT
 */
@Component
public class FixedRetryPolicy implements RetryPolicy {
    private final AIRetryProperties properties;
    private final BackoffStrategy backoffStrategy;
    public FixedRetryPolicy(AIRetryProperties properties, FixedBackoffStrategy fixedBackoffStrategy) {
        this.properties = properties;
        this.backoffStrategy = fixedBackoffStrategy;
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
        return RetryType.FIXED;
    }

}
