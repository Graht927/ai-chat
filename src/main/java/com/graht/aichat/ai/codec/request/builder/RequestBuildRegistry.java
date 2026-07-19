package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GRAHT
 */

@Component
public class RequestBuildRegistry implements BeanPostProcessor {
    private Map<AIProvider, HttpRequestBuilder> builders;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof HttpRequestBuilder builder) {
            HttpRequestBuilder old =
                    builders.putIfAbsent(builder.provider(), builder);

            if (old != null) {
                throw new AIException(AIErrorCode.AI_BUILDER_NOT_FOUND);
            }
            return bean;
        }
        return bean;
    }

    public HttpRequestBuilder findBuilder(AIProvider provider) {
        HttpRequestBuilder httpRequestBuilder = builders.get(provider);
        if (httpRequestBuilder == null) {
            throw new AIException(AIErrorCode.AI_PROVIDER_NOT_FOUND);
        }
        return httpRequestBuilder;
    }
}
