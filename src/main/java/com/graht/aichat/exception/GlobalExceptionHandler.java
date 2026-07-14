package com.graht.aichat.exception;

import com.graht.aichat.common.AIChatResult;
import com.graht.aichat.common.AIErrorCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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
    @ExceptionHandler
    public AIChatResult<String> handle(BusinessException e){
        return AIChatResult.fail(e.getResultCode().getMessage());
    }
    @ExceptionHandler
    public AIChatResult<String> handle(HandlerMethodValidationException e){
        e.printStackTrace();
        return AIChatResult.fail(e.getMessage());
    }
    @ExceptionHandler
    public AIChatResult<String> handle(MethodArgumentNotValidException e){
        e.printStackTrace();
        return AIChatResult.fail(e.getMessage());
    }
    @ExceptionHandler
    public AIChatResult<String> handle(AIException e){
        return AIChatResult.fail(e.getErrorCode());
    }
}
