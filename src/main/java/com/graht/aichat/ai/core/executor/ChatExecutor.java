package com.graht.aichat.ai.core.executor;

import com.graht.aichat.ai.core.domain.AIChatRequest;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AICapability;
import org.springframework.stereotype.Component;


/**
 * @author GRAHT
 */
@Component
public class ChatExecutor extends AbstractExecutor<AIChatRequest, AIResponse> implements AIExecutor<AIChatRequest,AIResponse> {


    @Override
    public AICapability capability() {
        return AICapability.CHAT;
    }

    @Override
    public Class<AIChatRequest> requestType() {
        return AIChatRequest.class;
    }
}
