package com.graht.aichat.ai.aop;


import com.graht.aichat.ai.domain.AIHttpResponse;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.exception.AIException;
import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

/**
 * @author GRAHT
 */
@Aspect
@Component
public class HttpProviderAdvice {

    private static final Logger log = LoggerFactory.getLogger(HttpProviderAdvice.class);

    @Around("@annotation(com.graht.aichat.ai.annotation.AIHttp)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
           return joinPoint.proceed();
        }catch (RestClientException e){
            throw new AIException(AIErrorCode.PROVIDER_ERROR);
        }finally {
            log.info(
                    "[{}] {} cost={}ms",
                    RequestContext.getRequestId(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start
            );
        }
    }
}
