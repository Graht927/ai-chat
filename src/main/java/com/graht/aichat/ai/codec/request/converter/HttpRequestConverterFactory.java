package com.graht.aichat.ai.codec.request.converter;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class HttpRequestConverterFactory {
    @Resource
    private HttpRequestConverterRegistry requestConverterRegistry;

    public HttpRequestConverter findConverter(ProviderCapabilityKey key) {
        return requestConverterRegistry.getConverter(key);
    }
}
