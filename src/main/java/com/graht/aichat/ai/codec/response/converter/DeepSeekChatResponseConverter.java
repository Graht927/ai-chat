package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.domain.TokenUsage;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.provider.deepseek.DeepSeekResponse;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekChatResponseConverter implements ResponseConverter<DeepSeekResponse> {
    @Override
    public AIResponse convert(DeepSeekResponse response) {
        return AIResponse.builder()
                .answer(response.getChoices().get(0).getMessage().getContent())
                .providerCapability(supportType())
                .model(response.getModel())
                .status("success")
                .tokenUsage(TokenUsage.builder()
                        .completionTokens(response.getUsage().getCompletion_tokens())
                        .promptTokens(response.getUsage().getPrompt_tokens())
                        .totalTokens(response.getUsage().getTotal_tokens())
                        .build())
                .build();
    }

    @Override
    public ProviderCapabilityKey supportType() {
        return ProviderCapabilityKey.of(AIProvider.DEEPSEEK, AICapability.CHAT);
    }
    @Override
    public Class<DeepSeekResponse> resType() {
        return DeepSeekResponse.class;
    }
}
