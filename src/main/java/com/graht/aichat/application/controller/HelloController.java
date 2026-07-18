package com.graht.aichat.application.controller;

import com.graht.aichat.common.AIChatResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/aa")
    public AIChatResult<String> aa(@Valid @RequestParam @NotBlank String aa){
        return AIChatResult.ok("hello world"+aa);
    }
}
