package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class ResponseConverterFactory {
    private ResponseConverterRegistry converterRegistry;
    public ResponseConverterFactory(ResponseConverterRegistry converterRegister){
        this.converterRegistry = converterRegister;
    }
    public <T> ResponseConverter<T> getConverter(ProviderCapabilityKey key, Class<?> clazz){

        ResponseConverter<?> converter = converterRegistry.getConverter(key);
        if (!converter.resType().equals(clazz)) {
            throw new AIException(AIErrorCode.RESPONSE_DUPLICATE_CONVERTER);
        }
        return (ResponseConverter<T>) converter;
    }
}
