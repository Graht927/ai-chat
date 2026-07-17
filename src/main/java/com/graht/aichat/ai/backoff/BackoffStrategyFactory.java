package com.graht.aichat.ai.backoff;

import com.graht.aichat.ai.retry.BackoffType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class BackoffStrategyFactory {
    @Resource
    private BackoffStrategyRegistry backoffStrategyRegistry;
    public BackoffStrategy getBackoffStrategy(BackoffType backoffType){
        return backoffStrategyRegistry.getBackoffStrategy(backoffType);
    }
}
