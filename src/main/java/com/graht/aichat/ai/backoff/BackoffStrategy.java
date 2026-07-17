package com.graht.aichat.ai.backoff;

import com.graht.aichat.ai.retry.BackoffType;
import com.graht.aichat.ai.retry.RetryContext;

/**
 * @author GRAHT
 */

public interface BackoffStrategy {
    long nextBackoffMillis(RetryContext context);
    BackoffType type();
}
