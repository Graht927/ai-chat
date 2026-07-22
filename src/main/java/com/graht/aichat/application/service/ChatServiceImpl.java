package com.graht.aichat.application.service;

import com.graht.aichat.ai.core.domain.AIChatRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.executor.ExecutorRegistry;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author GRAHT
 */
@Service
public class ChatServiceImpl implements ChatService{

    @Resource
    private ExecutorRegistry executorRegistry;
    @Override
    public AIResponse chat(AIChatRequest request) {
        return executorRegistry.execute(request);
    }
}
