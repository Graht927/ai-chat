package com.graht.aichat.common;

import lombok.Getter;

/**
 * @author GRAHT
 */

@Getter
public final class AIChatResult<T> {
    private final  Integer code;
    private final String message;
    private final  T data;

    private AIChatResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static AIChatResult<Void> ok(){
        return new AIChatResult<Void>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
    public static <T> AIChatResult<T> ok(T data){
        return new AIChatResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }
    public static <T> AIChatResult<T> fail(){
        return new AIChatResult<>(ResultCode.SYSTEM_ERROR.getCode(), ResultCode.SYSTEM_ERROR.getMessage(), null);
    }
    public static <T> AIChatResult<T> fail(T data){
        return new AIChatResult<> (ResultCode.SYSTEM_ERROR.getCode(), ResultCode.SYSTEM_ERROR.getMessage(), data);
    }
    public static Boolean isOk(AIChatResult<?> result){
        return result.code == ResultCode.SUCCESS.getCode();
    }
}
