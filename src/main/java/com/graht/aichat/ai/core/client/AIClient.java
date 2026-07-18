package com.graht.aichat.ai.core.client;

import com.graht.aichat.ai.core.domain.AIResult;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.ModelType;

/**
 * @author GRAHT
 *
 */

public interface AIClient {
    AIResult chat(AIRequest request);
    ModelType getModelType();
}
