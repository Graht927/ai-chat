package com.graht.aichat.exception;

import com.graht.aichat.common.AIErrorCode;

/**
 * @author GRAHT
 */

public class AIException extends RuntimeException{
    private final AIErrorCode errorCode;
    public AIException(AIErrorCode errorCode,String message,Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    public AIException(AIErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public AIException(AIErrorCode errorCode){
        this(errorCode,errorCode.getMessage());
    }
    public AIErrorCode getErrorCode() {
        return errorCode;
    }

}
