package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.backoff.BackoffStrategy;
import com.graht.aichat.ai.retry.RetryConfig;
import com.graht.aichat.ai.retry.RetryContext;
import com.graht.aichat.ai.retry.RetryPolicyType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */

@Component
public class NoRetryPolicy implements RetryPolicy {


    @Override
    public boolean canRetry(RetryContext context) {
        return false;
    }


    @Override
    public RetryPolicyType type() {
        return RetryPolicyType.NO;
    }

    @Override
    public RetryConfig config() {
        return null;
    }

    @Override
    public BackoffStrategy backoffStrategy() {
        return null;
    }
}
