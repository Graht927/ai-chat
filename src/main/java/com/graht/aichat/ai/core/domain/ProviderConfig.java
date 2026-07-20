package com.graht.aichat.ai.core.domain;

import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.infrastructure.retry.RetryPolicyType;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.common.EndpointType;
import com.graht.aichat.exception.AIException;
import lombok.Data;

import java.util.Map;

@Data
public class ProviderConfig {
    private String apiKey;
    private String baseUrl;
    private String defaultModel;
    private Map<String, ModelInfo> models;
    private Map<AICapability,String> endpoints;
    private RetryPolicyType retryPolicyType = RetryPolicyType.AI_REQUEST;
    public ModelInfo requireModel(String modelName){
        ModelInfo modelInfo = models.get(modelName);
        if (modelInfo == null) {
            throw new AIException(AIErrorCode.AI_MODEL_NOT_FOUND);
        }
        return modelInfo;
    }
    public ModelInfo getDefaultModel() {
        return requireModel(defaultModel);
    }
    public String requireEndpoint(AICapability capability) {
        String endpoint = endpoints.get(capability);
        if (endpoint == null) {
            throw new AIException(AIErrorCode.AI_ENDPOINT_NOT_FOUND);
        }
        return endpoint;
    }
}