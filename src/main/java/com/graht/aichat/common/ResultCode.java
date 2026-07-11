package com.graht.aichat.common;

/**
 * @author GRAHT
 */


public enum ResultCode {
    SUCCESS(200,"success"),
    SYSTEM_ERROR(500,"server fail"),
    ;
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
