package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class RequestBuildFactory {
    @Resource
    private RequestBuildRegistry requestBuildRegistry;
    public HttpRequestBuilder getBuilder(AIProvider provider) {
        return requestBuildRegistry.findBuilder(provider);
    }
}
