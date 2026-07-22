package com.graht.aichat.ai.core.executor;

import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AICapability;

public interface AIExecutor<Req extends AIRequest, Res extends AIResponse> {
     Res execute(Req request);
    AICapability capability();
    Class<Req> requestType();
}
