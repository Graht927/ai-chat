package com.graht.aichat.ai.backoff;

import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class FixedBackoffStrategy implements BackoffStrategy{
    private  final AIRetryProperties retryProperties;
    private  final long DEFAULT_BACKOFF_MILLIS;
    FixedBackoffStrategy(AIRetryProperties retryProperties) {
        this.retryProperties = retryProperties;
        DEFAULT_BACKOFF_MILLIS = retryProperties.getFixedDelay();
    }
    @Override
    public long nextBackoffMillis(int attempt) {
        return DEFAULT_BACKOFF_MILLIS;
    }
}
