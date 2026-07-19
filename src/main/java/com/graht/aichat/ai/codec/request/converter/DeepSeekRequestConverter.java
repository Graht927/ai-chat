package com.graht.aichat.ai.codec.request.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.model.AIProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekRequestConverter implements HttpRequestConverter{
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public String convert(RequestBuildContext context) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(context.getChatRequest());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    @Override
    public AIProvider provider() {
        return AIProvider.DEEPSEEK;
    }
}
