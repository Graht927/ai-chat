package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffType;
import com.graht.aichat.infrastructure.retry.RetryContext;
import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class FixedBackoffStrategy implements BackoffStrategy{
    private final long DEFAULT_BACKOFF_MILLIS;
    FixedBackoffStrategy(AIRetryProperties retryProperties) {
        DEFAULT_BACKOFF_MILLIS = retryProperties.getFixedDelay();
    }

    @Override
    public long nextBackoffMillis(RetryContext context) {
        return DEFAULT_BACKOFF_MILLIS;
    }

    @Override
    public BackoffType type() {
        return BackoffType.FIXED;
    }
}
