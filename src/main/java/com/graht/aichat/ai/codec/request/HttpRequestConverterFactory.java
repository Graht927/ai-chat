package com.graht.aichat.ai.codec.request;

import com.graht.aichat.ai.core.model.AIProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class HttpRequestConverterFactory {
    @Resource
    private HttpRequestConverterRegistry requestConverterRegistry;

    public HttpRequestConverter findConverter(AIProvider provider) {
        return requestConverterRegistry.getConverter(provider);
    }
}
