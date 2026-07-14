package com.graht.aichat.ai.client;

import com.graht.aichat.ai.credential.CredentialContext;
import com.graht.aichat.ai.domain.*;
import com.graht.aichat.ai.dto.AIHttpRequest;
import com.graht.aichat.ai.dto.AIRequest;
import com.graht.aichat.ai.dto.DeepSeekRequest;
import com.graht.aichat.ai.dto.DeepSeekResponse;
import com.graht.aichat.ai.http.AIHttpClient;
import com.graht.aichat.ai.model.ModelType;
import com.graht.aichat.ai.credential.Credential;
import com.graht.aichat.ai.credential.CredentialProvider;
import com.graht.aichat.common.RequestContext;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekClient implements AIClient{
    private static final Logger log = LoggerFactory.getLogger(DeepSeekClient.class);
    @Resource
    private CredentialProvider credentialProvider;
    private final AIHttpClient httpClient;
    public DeepSeekClient(AIHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public AIResult chat(AIRequest request) {
        CredentialContext context =
                CredentialContext.builder()
                        .modelType(getModelType())
                        .userId(request.getUserId())
                        .build();
        Credential credential =
                credentialProvider.getCredential(context);
        DeepSeekRequest deepSeekRequest = DeepSeekRequest.builder()
                .model(ModelType.DEEPSEEK.getModelVersion())
                .messages(List.of(
                        DeepSeekRequest.Message.builder()
                                .role("user")
                                .content(request.getPrompt())
                                .build()
                ))
                .stream(false)
                .build();
        AIHttpRequest httpRequest = AIHttpRequest.builder()
                .body(deepSeekRequest)
                .url(credential.getBaseUrl())
                .method(HttpMethod.POST)
                .headers(Map.of(
                        "Authorization","Bearer "+ credential.getApiKey(),
                        "Content-Type","application/json"
                        ))
                .build();
        AIHttpResponse httpResponse = httpClient.execute(httpRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        DeepSeekResponse deepSeekResponse = objectMapper.readValue(httpResponse.getBody(), DeepSeekResponse.class);
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
