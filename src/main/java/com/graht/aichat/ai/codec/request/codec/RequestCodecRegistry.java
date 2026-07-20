package com.graht.aichat.ai.codec.request.codec;

import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class RequestCodecRegistry implements BeanPostProcessor {
    private  Map<ProviderCapabilityKey, RequestCodec> requestCodecMap = new HashMap<>();
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof RequestCodec requestCodec){
            RequestCodec old = requestCodecMap.putIfAbsent(requestCodec.supportType(), requestCodec);
            if (old != null) {
                throw new AIException(AIErrorCode.AI_INIT_REQUEST_CODEC_ERROR, "Duplicate RequestCodec for ProviderCapabilityKey:" + requestCodec.supportType().toString());
            }
        }
        return bean;
    }
    public <T extends AIRequest> RequestCodec  getCodec(ProviderCapabilityKey  key) {
        RequestCodec codec = requestCodecMap.get(key);
        if (codec == null) {
            throw new AIException(AIErrorCode.AI_INIT_REQUEST_CODEC_ERROR, "No RequestCodec found for ProviderCapabilityKey: " + key.toString());
        }
        return codec;
    }

}
