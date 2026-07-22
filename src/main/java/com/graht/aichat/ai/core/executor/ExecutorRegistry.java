package com.graht.aichat.ai.core.executor;

import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ExecutorRegistry implements BeanPostProcessor {
    private final Map<AICapability,AIExecutor<?,?>> executors = new EnumMap<>(AICapability.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)  {
        if (bean instanceof AIExecutor<?,?> executor) {
            AICapability capability = executor.capability();
            AIExecutor<?, ?> aiExecutor = executors.putIfAbsent(capability, executor);
            if (aiExecutor != null){
                throw new AIException(AIErrorCode.AI_INIT_EXECUTOR_ERROR);
            }
        }
        return bean;
    }
    /**
     * 对外统一执行入口
     */
    public <Req extends AIRequest, Res extends AIResponse>
    Res execute(Req request){

        AIExecutor<Req,Res> executor =
                getExecutor(request.getAiCapability());
        if(!executor.requestType()
                .isAssignableFrom(request.getClass())){

            throw new AIException(
                    AIErrorCode.AI_REQUEST_TYPE_ERROR
            );
        }

        return executor.execute(request);
    }

    @SuppressWarnings("unchecked")
    public <Req extends AIRequest, Res extends AIResponse> AIExecutor<Req, Res> getExecutor(AICapability capability) {
        AIExecutor<?, ?> aiExecutor = executors.get(capability);
        if (aiExecutor == null) {
            throw new AIException(AIErrorCode.AI_EXECUTOR_NOT_FOUND);
        }
        return (AIExecutor<Req, Res>) aiExecutor;
    }
}
