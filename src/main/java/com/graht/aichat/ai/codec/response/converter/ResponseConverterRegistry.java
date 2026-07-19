package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class ResponseConverterRegistry implements BeanPostProcessor {
    private Map<AIProvider, ResponseConverter<?>> converterMap = new EnumMap<>(AIProvider.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof ResponseConverter<?> converter){
            AIProvider provider = converter.getProvider();
            ResponseConverter<?> old = converterMap.putIfAbsent(provider,converter);
            if (old != null) {
                throw new AIException(AIErrorCode.RESPONSE_DUPLICATE_CONVERTER, "Duplicate converter for provider: " + provider);
            }
        }
        return bean;
    }

    public ResponseConverter<?> getConverter(AIProvider provider){
        ResponseConverter<?> responseConverter = converterMap.get(provider);
        if (responseConverter == null) {
            throw new AIException(AIErrorCode.AI_RESPONSE_CONVERTER_NOT_FOUND, "No response converter found for provider: " + provider);
        }
        return responseConverter;
    }
}
