package com.graht.aichat.exception;

import com.graht.aichat.common.ResultCode;

/**
 * @author GRAHT
 */

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ResultCode resultCode;
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode() {
        return resultCode;
    }
}
