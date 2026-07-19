package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.domain.TokenUsage;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.provider.deepseek.DeepSeekResponse;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekResponseConverter implements ResponseConverter<DeepSeekResponse> {
    @Override
    public AIResponse convert(DeepSeekResponse response) {
        return AIResponse.builder()
                .answer(response.getChoices().get(0).getMessage().getContent())
                .provider(response.getProvider())
                .model(response.getModel())
                .tokenUsage(TokenUsage.builder()
                        .completionTokens(response.getUsage().getCompletion_tokens())
                        .promptTokens(response.getUsage().getPrompt_tokens())
                        .totalTokens(response.getUsage().getTotal_tokens())
                        .build())
                .build();
    }

    @Override
    public AIProvider getProvider() {
        return AIProvider.DEEPSEEK;
    }
}
