package com.graht.aichat.ai.provider.deepseek;

import com.graht.aichat.ai.codec.response.converter.ResponseConverter;
import com.graht.aichat.ai.codec.response.converter.ResponseConverterFactory;
import com.graht.aichat.ai.codec.response.parser.HttpResponseParser;
import com.graht.aichat.ai.codec.response.parser.HttpResponseParserFactory;
import com.graht.aichat.ai.core.client.AIClient;
import com.graht.aichat.ai.core.domain.*;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.transport.AIHttpClient;
import com.graht.aichat.ai.codec.request.builder.HttpRequestBuilder;
import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.codec.request.builder.RequestBuildFactory;
import com.graht.aichat.ai.codec.request.converter.HttpRequestConverterFactory;
import com.graht.aichat.ai.provider.ProviderFactory;
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
    @Resource
    private ResponseConverterFactory responseConverterFactory;

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

        HttpResponseParser<DeepSeekResponse> parser = responseParserFactory.getParser(request.getProvider());
        DeepSeekResponse deepSeekResponse = parser.parse(httpResponse);

        ResponseConverter<DeepSeekResponse> converter = responseConverterFactory.getConverter(request.getProvider());
        AIResponse res = converter.convert(deepSeekResponse);

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
