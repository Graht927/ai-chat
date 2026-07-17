package com.graht.aichat.ai.retry;

import com.graht.aichat.ai.backoff.BackoffConfig;
import com.graht.aichat.common.AIErrorCode;
import lombok.Data;

import java.util.Set;

@Data
public class RetryConfig {

    /**
     * 最大尝试次数
     */
    private int maxAttempts;

    /**
     * 可以重试的异常
     */
    private Set<AIErrorCode> retryableErrors;

    /**
     * 等待策略
     */
    private BackoffConfig backoff;

}