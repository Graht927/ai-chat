package com.graht.aichat.ai.retry;

import com.graht.aichat.ai.policy.RetryPolicy;
import com.graht.aichat.ai.policy.RetryPolicyFactory;
import com.graht.aichat.ai.policy.RetryPolicyRegistry;
import com.graht.aichat.common.RequestContext;
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

    public <T> T execute(RetryType retryType,Supplier<T> supplier){
        RetryPolicy retryPolicy = retryPolicyFactory.getRetryPolicy(retryType);
        int attempt = 0;
        while (true){
            try {
                return supplier.get();
            }catch (Exception e){
                if(!retryPolicy.canRetry(attempt, e)){
                    throw e;
                }
                attempt++;
                sleep(retryPolicy.nextBackoff(attempt));
            }finally {
                log.warn(
                        "[{}] Retry {}/{} after {} ms, cause={}",
                        RequestContext.getRequestId(),
                        attempt
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
