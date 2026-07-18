package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffType;
import com.graht.aichat.infrastructure.retry.RetryContext;

/**
 * @author GRAHT
 */

public interface BackoffStrategy {
    long nextBackoffMillis(RetryContext context);
    BackoffType type();
}
