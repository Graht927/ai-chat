package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.DeepSeekRequest;
import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.transport.AIHttpRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
    public AIHttpRequest build(RequestBuildContext<?> context, String payload) {
        ProviderConfig provider = context.getProviderConfig();
        AICapability capability = context.getAiCapability();
        String endpoint = provider.requireEndpoint(capability);
        String apiKey = provider.getApiKey();
        String baseUrl = provider.getBaseUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + apiKey);
        return AIHttpRequest.builder()
                .url(baseUrl + endpoint)
                .headers(headers)
                .method(HttpMethod.POST)
                .body(payload)
                .build();
    }

    @Override
    public AIProvider provider(){
        return AIProvider.DEEPSEEK;
    }
}
