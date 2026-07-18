package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.BackoffConfig;
import com.graht.aichat.infrastructure.retry.BackoffStrategy;
import com.graht.aichat.infrastructure.retry.BackoffStrategyFactory;
import com.graht.aichat.infrastructure.retry.RetryListenerManager;
import com.graht.aichat.infrastructure.retry.RetryPolicy;
import com.graht.aichat.infrastructure.retry.RetryPolicyFactory;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.common.ResultCode;
import com.graht.aichat.exception.BusinessException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;


/**
 * @author GRAHT
 */

@Component
public class RetryExecutor {
    private static final Logger log = LoggerFactory.getLogger(RetryExecutor.class);
    @Resource
    private RetryPolicyFactory retryPolicyFactory;
    @Resource
    private RetryListenerManager listenerManager;

    public <T> T execute(RetryPolicyType retryPolicyType, RetryCallable<T> callable){
        RetryPolicy retryPolicy = retryPolicyFactory.getRetryPolicy(retryPolicyType);
        RetryContext context  = RetryContext.builder()
                .retryPolicyType(retryPolicyType)
                .build();
        listenerManager.onStart(context);
        while (true){
            try {
                T result = callable.call();
                context.recordResult(result);
                context.markSuccess();
                listenerManager.onComplete(context);
                return result;
            }catch (Exception e){
                context.recordException(e);
                context.markAttemptFailed();
                listenerManager.onAttemptFailed(context);
                if(!retryPolicy.canRetry(context)){
                    listenerManager.onComplete(context);
                    throw new BusinessException(ResultCode.AI_ERROR);
                }
                context.incrementRetryCount();
                context.markRetrying();
                listenerManager.onRetry(context);
                sleep(retryPolicy.
                        backoffStrategy().
                        nextBackoffMillis(context)
                );
            }
        }


    }
    void sleep(long millis){
        if (millis < 0) return;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Retry interrupted",e);
        }
    }
}
