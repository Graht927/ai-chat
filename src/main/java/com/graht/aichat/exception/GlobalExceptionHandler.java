package com.graht.aichat.exception;

import com.graht.aichat.common.AIChatResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author GRAHT
 */
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler
    public AIChatResult<String> handle(Exception e){
        //TODO change logger
        e.printStackTrace();
        return AIChatResult.fail(e.getMessage());
    }
}
