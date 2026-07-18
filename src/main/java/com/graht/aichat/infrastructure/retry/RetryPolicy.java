package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffStrategy;
import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryContext;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;

/**
 * @author GRAHT
 */

public interface RetryPolicy {
    boolean canRetry(RetryContext context);
    RetryPolicyType type();
    RetryConfig config();
    BackoffStrategy backoffStrategy();
}
