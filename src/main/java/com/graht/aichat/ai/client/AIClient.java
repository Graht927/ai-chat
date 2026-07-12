package com.graht.aichat.ai.client;

import com.graht.aichat.ai.domain.AIResponse;
import com.graht.aichat.ai.dto.AIRequest;
import com.graht.aichat.ai.model.ModelType;

/**
 * @author GRAHT
 *
 */

public interface AIClient {
    AIResponse chat(AIRequest request);
    ModelType getModelType();
}
