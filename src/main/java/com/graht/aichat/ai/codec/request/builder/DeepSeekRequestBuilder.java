package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.domain.DeepSeekRequest;
import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekRequestBuilder implements HttpRequestBuilder{

    @Override
    public HttpRequest build(RequestBuildContext<?> context,String payload) {
        ProviderConfig provider = context.getProviderConfig();
        AICapability capability = context.getAiCapability();
        String endpoint = provider.requireEndpoint(capability);
        String apiKey = provider.getApiKey();
        String baseUrl = provider.getBaseUrl();
        return HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .headers("Content-Type", "application/json","Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
    }


    @Override
    public AIProvider provider(){
        return AIProvider.DEEPSEEK;
    }
}
