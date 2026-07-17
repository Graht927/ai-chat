package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.backoff.BackoffStrategy;
import com.graht.aichat.ai.retry.RetryConfig;
import com.graht.aichat.ai.retry.RetryContext;
import com.graht.aichat.ai.retry.RetryPolicyType;

/**
 * @author GRAHT
 */

public interface RetryPolicy {
    boolean canRetry(RetryContext context);
    RetryPolicyType type();
    RetryConfig config();
    BackoffStrategy backoffStrategy();
}
