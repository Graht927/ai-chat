package com.graht.aichat.ai.codec.response.parser;

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
    public DeepSeekResponse parse(AIHttpResponse  response) {
        try {
            return objectMapper.readValue(response.getBody(), DeepSeekResponse.class);
        } catch (Exception e) {
            throw new AIException(AIErrorCode.INVALID_RESPONSE, e.getMessage());
        }
    }

    @Override
    public AIProvider getProvider() {
        return AIProvider.DEEPSEEK;
    }
}
