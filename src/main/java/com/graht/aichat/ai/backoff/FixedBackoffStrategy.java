package com.graht.aichat.ai.backoff;

import com.graht.aichat.ai.retry.BackoffType;
import com.graht.aichat.ai.retry.RetryContext;
import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class FixedBackoffStrategy implements BackoffStrategy{
    private  final long DEFAULT_BACKOFF_MILLIS;
    FixedBackoffStrategy(AIRetryProperties retryProperties) {
        DEFAULT_BACKOFF_MILLIS = retryProperties.getFixedDelay();
    }

    @Override
    public long nextBackoffMillis(RetryContext context) {
        return DEFAULT_BACKOFF_MILLIS;
    }

    @Override
    public BackoffType type() {
        return BackoffType.EXPONENTIAL;
    }
}
