package com.graht.aichat.common;

/**
 * @author GRAHT
 */


public enum ResultCode {
    SUCCESS(200,"success"),
    SYSTEM_ERROR(50000,"server fail"),
    VALIDATION_FAILED(40001,"validation failed"),
    BUSINESS_ERROR(50001,"business error"),
    INVALID_PARAMETER(600,"invalid parameter"),
    PARAM_VALIDATION_FAILED(601,"param validation failed"),
    AI_CLIENT_NOT_FOUND(50002,"AI Client not found" );
    private final int code;
    private final String message;
    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
