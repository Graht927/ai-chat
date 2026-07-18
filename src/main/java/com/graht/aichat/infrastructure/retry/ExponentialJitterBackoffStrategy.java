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
public class ExponentialJitterBackoffStrategy implements BackoffStrategy{
    private final long baseDelay;
    private final long maxDelay;
    public ExponentialJitterBackoffStrategy(AIRetryProperties properties) {
        baseDelay = properties.getBaseDelay();
        maxDelay = properties.getMaxDelay();
    }

    @Override
    public long nextBackoffMillis(RetryContext context) {
        long exponential =
                baseDelay * (1L << context.getRetryCount());


        long jitter = ThreadLocalRandom.current().nextLong(100);


        return Math.min(
                exponential + jitter,
                maxDelay
        );}

    @Override
    public BackoffType type() {
        return BackoffType.EXPONENTIAL_JITTER;
    }
}
