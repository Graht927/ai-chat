package com.graht.aichat.controller;

import com.graht.aichat.common.AIChatResult;
import com.graht.aichat.dto.ChatRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GRAHT
 */
@RestController("/v1/chat")
public class ChatController {

    @GetMapping("/")
    public AIChatResult<String> chat(@Valid @RequestParam ChatRequest  request){

        return AIChatResult.ok("hello world");
    }
}
