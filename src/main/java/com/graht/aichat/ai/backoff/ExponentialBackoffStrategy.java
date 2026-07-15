package com.graht.aichat.ai.backoff;

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
    private final AIRetryProperties properties;
    public ExponentialBackoffStrategy(AIRetryProperties properties) {
        this.properties = properties;
        DEFAULT_DELAY = properties.getBaseDelay();
        MAX_DELAY = properties.getMaxDelay();
    }
    @Override
    public long nextBackoffMillis(int attempt) {
            long delay = (long) DEFAULT_DELAY << attempt;
            return Math.min(delay, MAX_DELAY)+ ThreadLocalRandom.current().nextLong(100);
    }
}
