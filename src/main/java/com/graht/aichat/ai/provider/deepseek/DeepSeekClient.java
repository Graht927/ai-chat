package com.graht.aichat.ai.provider.deepseek;

import com.graht.aichat.ai.codec.response.HttpResponseParser;
import com.graht.aichat.ai.codec.response.HttpResponseParserFactory;
import com.graht.aichat.ai.core.client.AIClient;
import com.graht.aichat.ai.core.domain.*;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.transport.AIHttpClient;
import com.graht.aichat.ai.codec.request.HttpRequestBuilder;
import com.graht.aichat.ai.codec.request.RequestBuildContext;
import com.graht.aichat.ai.codec.request.RequestBuildFactory;
import com.graht.aichat.ai.codec.request.HttpRequestConverterFactory;
import com.graht.aichat.ai.provider.ProviderFactory;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.infrastructure.retry.RetryExecutor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.net.http.HttpRequest;
/**
 * @author GRAHT
 */
@Component
public class DeepSeekClient implements AIClient {
    private static final Logger log = LoggerFactory.getLogger(DeepSeekClient.class);
    private final AIHttpClient httpClient;
    public DeepSeekClient(AIHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    @Resource
    private RetryExecutor retryExecutor;
    @Resource
    private ProviderFactory providerFactory;
    @Resource
    private HttpRequestConverterFactory httpRequestConverterFactory;
    @Resource
    private RequestBuildFactory requestBuildFactory;
    @Resource
    private HttpResponseParserFactory responseParserFactory;

    @Override
    public AIResult chat(AIRequest request) {
        //Building request parameters
        ProviderConfig provider = providerFactory.getProvider(request.getProvider());
        ModelInfo modelInfo = provider.requireModel(request.getModel());
        RequestBuildContext<AIRequest> context = RequestBuildContext.<AIRequest>builder()
                .chatRequest(request)
                .modelInfo(modelInfo)
                .requestId(request.getRequestId())
                .modelName(request.getModel())
                .provider(request.getProvider())
                .endpointType(request.getEndpointType())
                .providerConfig(provider)
                .build();
        HttpRequestBuilder<AIRequest> builder = requestBuildFactory.getBuilder(request.getProvider());
        HttpRequest httpRequest = builder.build(context);
        AIHttpResponse httpResponse = retryExecutor.execute(provider.getRetryPolicyType(),() -> httpClient.execute(httpRequest));
        HttpResponseParser parser = responseParserFactory.getParser(request.getProvider());
        AIResponse res = parser.parse(httpResponse);
        log.info("[{}-DeepSeekClient]deepseek response: {}",MDC.get("requestId"), res);
        return AIResult.builder()
                .answer(res.getAnswer())
                .requestId(request.getRequestId())
                .tokenUsage(res.getTokenUsage())
                .build();
    }

    @Override
    public AIProvider getProvider() {
        return AIProvider.DEEPSEEK;
    }
}
