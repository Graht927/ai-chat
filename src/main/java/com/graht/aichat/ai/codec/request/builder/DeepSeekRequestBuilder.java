package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.codec.request.converter.DeepSeekRequestConverter;
import com.graht.aichat.ai.core.model.AIProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekRequestBuilder implements HttpRequestBuilder{
    @Resource
    private DeepSeekRequestConverter deepSeekRequestConverter;
    @Override
    public HttpRequest build(RequestBuildContext context) {
        ProviderConfig provider = context.getProviderConfig();
        String endpoint = provider.getEndpoints().get(context.getEndpointType());
        String apiKey = provider.getApiKey();
        String baseUrl = provider.getBaseUrl();
        return HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .headers("Content-Type", "application/json","Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(deepSeekRequestConverter.convert(context)))
                .build();
    }

    @Override
    public AIProvider provider() {
        return AIProvider.DEEPSEEK;
    }
}
