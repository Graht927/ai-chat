package com.graht.aichat.ai.codec.request.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.domain.AIChatRequest;
import com.graht.aichat.ai.core.domain.DeepSeekRequest;
import com.graht.aichat.ai.core.domain.Message;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.provider.deepseek.DeepSeekResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekChatRequestConverter implements HttpRequestConverter<DeepSeekRequest>{
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public String convert(RequestBuildContext<?> context) {
        AIChatRequest request = (AIChatRequest) context.getRequest();
        Message msg = Message.builder().role("user").content(request.getMessage()).build();


        String s = null;
        try {
            s = objectMapper.writeValueAsString(DeepSeekRequest.builder()
                    .messages(List.of(msg)).model(context.getModelName()).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    @Override
    public ProviderCapabilityKey supportType() {
        return ProviderCapabilityKey.of(AIProvider.DEEPSEEK, AICapability.CHAT);
    }
}
