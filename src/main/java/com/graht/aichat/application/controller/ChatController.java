package com.graht.aichat.application.controller;

import com.graht.aichat.ai.core.domain.AIChatRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.common.AIChatResult;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.application.dto.ChatRequest;
import com.graht.aichat.application.service.ChatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author GRAHT
 */
@RestController
@RequestMapping("/v1/chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    @Resource
    private ChatService chatService;

    @PostMapping
    public AIChatResult<AIResponse> chat(@Valid @RequestBody AIChatRequest request){
        log.info(RequestContext.getRequestId() + "Received chat request: {}", request);
        return AIChatResult.ok(chatService.chat(request));
    }
}
