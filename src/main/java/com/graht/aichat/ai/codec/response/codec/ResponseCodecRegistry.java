package com.graht.aichat.ai.codec.response.codec;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GRAHT
 */
@Component
public class ResponseCodecRegistry implements BeanPostProcessor {
    private Map<ProviderCapabilityKey,ResponseCodec<?>> responseCodecMap = new ConcurrentHashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ResponseCodec<?> responseCodec) {
            ResponseCodec<?> responseCodec1 = responseCodecMap.putIfAbsent(responseCodec.supportType(), responseCodec);
            if (responseCodec1 != null){
                throw  new AIException(AIErrorCode.AI_INIT_RESPONSE_CODEC_ERROR);
            }
        }
        return bean;
    }
    @SuppressWarnings("unchecked")
    public <T extends AIResponse> ResponseCodec<T> getResponseCodec(ProviderCapabilityKey key){
        ResponseCodec<?> responseCodec = responseCodecMap.get(key);
        if (responseCodec == null) {
            throw new AIException(AIErrorCode.AI_INIT_RESPONSE_CODEC_ERROR);
        }
        return (ResponseCodec<T>) responseCodec;
    }
}
