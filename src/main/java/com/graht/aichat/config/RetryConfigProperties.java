package com.graht.aichat.config;

import com.graht.aichat.infrastructure.retry.RetryConfig;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */
@ConfigurationProperties(prefix = "ai.retry.config")
@Component
@Getter
@Setter
public class RetryConfigProperties {
    /**
     * 不同策略的配置
     */
    private Map<RetryPolicyType, RetryConfig> policies;


}
