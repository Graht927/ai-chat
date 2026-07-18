package com.graht.aichat.ai.provider.deepseek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graht.aichat.ai.core.client.AIClient;
import com.graht.aichat.ai.core.domain.*;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.transport.AIHttpClient;
import com.graht.aichat.ai.codec.request.HttpRequestBuilder;
import com.graht.aichat.ai.codec.request.RequestBuildContext;
import com.graht.aichat.ai.codec.request.RequestBuildFactory;
import com.graht.aichat.ai.codec.request.HttpRequestConverterFactory;
import com.graht.aichat.ai.core.model.ModelType;
import com.graht.aichat.ai.provider.ProviderFactory;
import com.graht.aichat.infrastructure.retry.RetryExecutor;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        ObjectMapper objectMapper = new ObjectMapper();
        DeepSeekResponse deepSeekResponse = null;
        try {
        deepSeekResponse = objectMapper.readValue(httpResponse.getBody(), DeepSeekResponse.class);
        }catch (JsonProcessingException e) {
            throw new AIException(AIErrorCode.INVALID_RESPONSE);
        }
        AIResponse aiResponse = AIResponse.builder()
                .answer(deepSeekResponse.getChoices().get(0).getMessage().getContent())
                .provider(getModelType().getModelVersion())
                .model(getModelType().getModelName())
                .build();
        TokenUsage usage = TokenUsage.builder()
                .promptTokens(deepSeekResponse.getUsage().getPrompt_tokens())
                .completionTokens(deepSeekResponse.getUsage().getCompletion_tokens())
                .totalTokens(deepSeekResponse.getUsage().getTotal_tokens())
                .build();
        log.info(request.getRequestId() + "DeepSeekResponse:" + deepSeekResponse);
        return AIResult.builder()
                .answer(aiResponse.getAnswer())
                .requestId(request.getRequestId())
                .build();
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DEEPSEEK;
    }
}
