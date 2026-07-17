package com.graht.aichat.ai.backoff;

import com.graht.aichat.ai.retry.BackoffType;
import lombok.Data;

@Data
public class BackoffConfig {
    private BackoffType type = BackoffType.FIXED;

    /**
     * 固定等待(ms)
     */
    private long fixedDelay = 1000;

    /**
     * 指数退避初始等待(ms)
     */
    private long baseDelay = 500;

    /**
     * 最大等待(ms)
     */
    private long maxDelay = 5000;

    /**
     * Jitter(随机抖动)
     */
    private long jitter = 100;

}