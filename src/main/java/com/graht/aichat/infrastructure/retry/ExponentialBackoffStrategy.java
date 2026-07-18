package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffType;
import com.graht.aichat.infrastructure.retry.RetryContext;
import com.graht.aichat.config.AIRetryProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author GRAHT
 */
@Component
public class ExponentialBackoffStrategy implements BackoffStrategy{
    private  final long DEFAULT_DELAY;
    private  final long MAX_DELAY;
    public ExponentialBackoffStrategy(AIRetryProperties properties) {
        DEFAULT_DELAY = properties.getBaseDelay();
        MAX_DELAY = properties.getMaxDelay();
    }

    @Override
    public long nextBackoffMillis(RetryContext context) {
        long delay = DEFAULT_DELAY * (1L << context.getRetryCount());
        return Math.min(delay, MAX_DELAY);
    }

    @Override
    public BackoffType type() {
        return BackoffType.EXPONENTIAL;
    }
}
