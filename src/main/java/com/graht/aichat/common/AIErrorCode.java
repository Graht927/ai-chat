package com.graht.aichat.common;

public enum AIErrorCode {

    TIMEOUT(
            5001,
            "AI service request timeout"
    ),

    AUTH_FAILED(
            5002,
            "AI service authentication failed"
    ),

    RATE_LIMIT(
            5003,
            "AI service rate limit exceeded"
    ),

    PROVIDER_ERROR(
            5004,
            "AI provider service error"
    ),

    INVALID_RESPONSE(
            5005,
            "Invalid response from AI provider"
    ),

    UNKNOWN(
            5999,
            "Unknown AI service error"
    );


    private final Integer code;
    private final String message;


    AIErrorCode(
            Integer code,
            String message
    ){
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}