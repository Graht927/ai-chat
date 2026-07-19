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
    PROVIDER_UNAVAILABLE(
            5006,
            "AI provider service unavailable"
    ),
    UNKNOWN(
            5999,
            "Unknown AI service error"
    ),
    RETRY_CONFIG_NOT_FOUND(5007, "Retry config not found"),
    AI_PROVIDER_NOT_FOUND(5008, "AI provider not found" ),
    AI_MODEL_NOT_FOUND(5009, "AI model not found" ),
    AI_ENDPOINT_NOT_FOUND(5010, "AI endpoint not found" ),
    AI_CONVERTER_NOT_FOUND(5011, "AI converter not found" ),
    AI_BUILDER_NOT_FOUND(5012, "AI builder not found"),
    AI_INIT_PARSER_ERROR(5013, "AI init parser error" ),
    AI_PARSER_NOT_FOUND(5014, "AI parser not found" ),
    AI_REQUEST_ERROR(5015, "AI request error" ),
    RESPONSE_DUPLICATE_CONVERTER(5016, "Response duplicate converter"),
    AI_RESPONSE_CONVERTER_NOT_FOUND(5017, "AI response converter not found" );


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