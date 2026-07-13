package com.graht.aichat.ai.client;

import ch.qos.logback.core.util.StringUtil;
import com.graht.aichat.ai.domain.AIResponse;
import com.graht.aichat.ai.dto.AIRequest;
import com.graht.aichat.ai.model.ModelType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekClient implements AIClient{
    @Override
    public AIResponse chat(AIRequest request) {
        ModelType modelType = getModelType();
        return AIResponse.builder()
                .provider(modelType.getModelName())
                .model(modelType.getModelName()+"-"+modelType.getModelVersion())
                .answer("Hello, I am DeepSeek.")
                .requestId(request.getRequestId())
                .build();
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DEEPSEEK;
    }
}
