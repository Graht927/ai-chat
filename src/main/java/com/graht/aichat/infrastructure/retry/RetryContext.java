package com.graht.aichat.infrastructure.retry;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author GRAHT
 */
@Builder
@Getter
public class RetryContext {
    private Object lastResult;
    private Throwable lastException;
    @Setter
    private RetryPolicyType retryPolicyType;
    private String requestId;
    @Builder.Default
    private int retryCount = 0;
    @Builder.Default
    private Instant startTime = Instant.now();
    @Builder.Default
    private RetryStatusEnum status = RetryStatusEnum.CREATED;

    public void incrementRetryCount() {
        retryCount++;
    }
    public void recordException(Throwable throwable){
        this.lastException = throwable;
    }
    public void recordResult(Object result){
        this.lastResult = result;
    }
    private void markStatus(RetryStatusEnum status){
        this.status = status;
    }
    public void markAttemptFailed(){
        markStatus(RetryStatusEnum.ATTEMPT_FAILED);
    }

    public void markRetrying(){
        markStatus(RetryStatusEnum.RETRYING);
    }
    public void markFailed(){
        markStatus(RetryStatusEnum.FAILED);
    }
    public void markCreated(){
        markStatus(RetryStatusEnum.CREATED);
    }
    public void markRunning(){
        markStatus(RetryStatusEnum.RUNNING);
    }
    public void markSuccess(){
        markStatus(RetryStatusEnum.SUCCESS);
    }
    public boolean isRetrying(){
        return status == RetryStatusEnum.RETRYING;
    }
    public boolean isSuccess(){
        return status == RetryStatusEnum.SUCCESS;
    }
    public boolean isFinished(){
        return status == RetryStatusEnum.SUCCESS
                || status == RetryStatusEnum.FAILED;
    }


}
