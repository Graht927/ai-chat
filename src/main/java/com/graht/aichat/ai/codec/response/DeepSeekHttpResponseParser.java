package com.graht.aichat.ai.codec.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.domain.TokenUsage;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.provider.deepseek.DeepSeekResponse;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekHttpResponseParser implements HttpResponseParser<DeepSeekResponse>{
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public AIResponse parse(AIHttpResponse  response) {
        try {
            DeepSeekResponse deepSeekResponse = objectMapper.readValue(response.getBody(), DeepSeekResponse.class);
            TokenUsage usage = TokenUsage.builder()
                    .promptTokens(deepSeekResponse.getUsage().getPrompt_tokens())
                    .completionTokens(deepSeekResponse.getUsage().getCompletion_tokens())
                    .totalTokens(deepSeekResponse.getUsage().getTotal_tokens())
                    .build();
            AIResponse aiResponse = AIResponse.builder()
                    .answer(deepSeekResponse.getChoices().get(0).getMessage().getContent())
                    .provider(deepSeekResponse.getProvider())
                    .model(deepSeekResponse.getModel())
                    .tokenUsage(usage)
                    .build();
            return aiResponse;
        } catch (Exception e) {
            throw new AIException(AIErrorCode.INVALID_RESPONSE, e.getMessage());
        }
    }

    @Override
    public AIProvider getProvider() {
        return AIProvider.DEEPSEEK;
    }
}
