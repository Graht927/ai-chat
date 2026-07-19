package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.model.AIProvider;
import jakarta.annotation.Resource;
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
    public <T> ResponseConverter<T> getConverter(AIProvider provider){
        return (ResponseConverter<T>) converterRegistry.getConverter(provider);
    }
}
