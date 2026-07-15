package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.retry.RetryType;
import com.graht.aichat.common.AIErrorCode;

import java.util.concurrent.Callable;

/**
 * @author GRAHT
 */

public interface RetryPolicy {
    boolean canRetry(int attempt, Exception e);
    long nextBackoff(int attempt);
    RetryType type();
}
