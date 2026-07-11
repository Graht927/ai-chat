package com.graht.aichat.controller;

import com.graht.aichat.common.AIChatResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author GRAHT
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public AIChatResult<String> hello(){
        return AIChatResult.ok("hello world");
    }
}
