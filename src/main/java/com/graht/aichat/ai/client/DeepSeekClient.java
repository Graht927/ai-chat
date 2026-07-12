package com.graht.aichat.ai.client;

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
        return null;
    }

    @Override
    public ModelType getModelType() {
        return ModelType.DEEPSEEK;
    }
}
