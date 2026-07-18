package com.graht.aichat.infrastructure.retry;

/**
 * @author GRAHT
 */


public enum RetryStatusEnum {
    SUCCESS,
    FAILED,
    RETRYING,
    CREATED,
    RUNNING,
    ATTEMPT_FAILED;
}
