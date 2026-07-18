package com.graht.aichat.ai.codec.request;

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
public class HttpRequestConverterRegistry implements BeanPostProcessor {
    private Map<AIProvider, HttpRequestConverter> converters;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof HttpRequestConverter converter) {
            HttpRequestConverter old =
                    converters.putIfAbsent(converter.provider(), converter);

            if (old != null) {
                throw new AIException(AIErrorCode.AI_CONVERTER_NOT_FOUND);
            }
        }
        return bean;
    }
    public HttpRequestConverter getConverter(AIProvider provider) {
        HttpRequestConverter converter = converters.get(provider);
        if (converter == null){
            throw new AIException(AIErrorCode.AI_CONVERTER_NOT_FOUND);
        }
        return converter;
    }
}
