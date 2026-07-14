package com.graht.aichat.controller;

import com.graht.aichat.ai.domain.AIResponse;
import com.graht.aichat.common.AIChatResult;
import com.graht.aichat.common.RequestAttributes;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.dto.ChatRequest;
import com.graht.aichat.service.ChatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public AIChatResult<AIResponse> chat(@Valid @RequestBody ChatRequest  request){
        log.info(RequestContext.getRequestId() + "Received chat request: {}", request);
        return AIChatResult.ok(chatService.chat(request));
    }
}
