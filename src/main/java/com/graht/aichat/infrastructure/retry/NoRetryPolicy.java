package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffStrategy;
import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryContext;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
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
