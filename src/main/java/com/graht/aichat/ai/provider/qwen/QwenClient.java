package com.graht.aichat.ai.provider.qwen;

import com.graht.aichat.ai.core.client.AIClient;
import com.graht.aichat.ai.core.domain.AIResult;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ModelType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */

@Component
public class QwenClient implements AIClient {
    @Override
    public AIResult chat(AIRequest request) {
        return null;
    }


    @Override
    public AIProvider getProvider() {
        return AIProvider.QWEN;
    }
}
