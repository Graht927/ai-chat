package com.graht.aichat.ai.core.client;

import com.graht.aichat.ai.core.model.ModelType;
import com.graht.aichat.common.ResultCode;
import com.graht.aichat.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GRAHT
 */
@Component

public class AIClientRegistry implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(AIClientRegistry.class);
    private final Map<ModelType, AIClient> clients = new ConcurrentHashMap<>();
    @Override
    public Object postProcessAfterInitialization(Object client, String beanName) {
        if (client instanceof AIClient) {
            AIClient aiClient = clients.putIfAbsent(((AIClient) client).getModelType(), (AIClient) client);
            if (aiClient != null) {
                throw new IllegalArgumentException("Duplicate AIClient bean for model type: " + ((AIClient) client).getModelType());
            }
            log.info(
                    "Registered AIClient [{}] for {}",
                    beanName,
                    ((AIClient) client).getModelType()
            );
        }
        return client;
    }
    public AIClient getClient(ModelType modelType) {
        if (modelType == null) throw new BusinessException(ResultCode.INVALID_PARAMETER, "Model type cannot be null");
        AIClient aiClient = clients.get(modelType);
        if (aiClient == null){
            throw new BusinessException(ResultCode.AI_CLIENT_NOT_FOUND, "AI Client not found: " + modelType);
        }

        return aiClient;
    }
}
