package com.graht.aichat.application.service;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.application.dto.ChatRequest;

/**
 * @author GRAHT
 */

public interface ChatService {
    AIResponse chat(ChatRequest request);
}
