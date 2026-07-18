package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffType;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class BackoffStrategyRegistry implements BeanPostProcessor {

    private final Map<BackoffType, BackoffStrategy> backoffStrategies = new EnumMap<>(BackoffType.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof BackoffStrategy backoffStrategy) {
            BackoffStrategy strategy = this.backoffStrategies.putIfAbsent(backoffStrategy.type(), backoffStrategy);
            if (strategy != null) {
                throw new IllegalArgumentException("Duplicate backoff strategy for type: " + backoffStrategy.type());
            }
        }
        return bean;
    }
    public BackoffStrategy getBackoffStrategy(BackoffType backoffType){
        BackoffStrategy backoffStrategy = backoffStrategies.get(backoffType);
        if (backoffStrategy == null) {
            throw new IllegalArgumentException("No backoff strategy found for type: " + backoffType);
        }
        return backoffStrategy;
    }
}
