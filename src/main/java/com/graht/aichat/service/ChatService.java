package com.graht.aichat.service;

import com.graht.aichat.ai.domain.AIResponse;
import com.graht.aichat.dto.ChatRequest;
import org.springframework.stereotype.Service;

/**
 * @author GRAHT
 */

public interface ChatService {
    AIResponse chat(ChatRequest request);
}
