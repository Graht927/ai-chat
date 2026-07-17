package com.graht.aichat.config;

import com.graht.aichat.ai.retry.BackoffType;
import com.graht.aichat.ai.retry.RetryPolicyType;
import com.graht.aichat.common.AIErrorCode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author GRAHT
 */
@Component
@ConfigurationProperties(prefix = "ai.retry")
@Data
public class AIRetryProperties {
    /**
     * 默认重试策略
     */
    private RetryPolicyType policy = RetryPolicyType.DEFAULT;
    /**
     * 默认重试方法类型
     */
    private BackoffType backoff = BackoffType.FIXED;

    /**
     * 最大重试次数
     */
    private int maxRetries = 3;

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

    /**
     * 可重试的错误码
     */
    private Set<AIErrorCode> retryableErrors;
}
