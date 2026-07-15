package com.graht.aichat.ai.backoff;

/**
 * @author GRAHT
 */

public interface BackoffStrategy {
    long nextBackoffMillis(int retryCount);
}
