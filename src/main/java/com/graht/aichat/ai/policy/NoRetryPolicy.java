package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.retry.RetryType;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author GRAHT
 */

@Component
public class NoRetryPolicy implements RetryPolicy {

    @Override
    public boolean canRetry(int attempt, Exception e) {
        return false;
    }

    @Override
    public long nextBackoff(int attempt) {
        return 0;
    }

    @Override
    public RetryType type() {
        return RetryType.NO;
    }
}
