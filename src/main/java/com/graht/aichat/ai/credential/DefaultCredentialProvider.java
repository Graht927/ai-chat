package com.graht.aichat.ai.credential;

import com.graht.aichat.ai.model.ModelType;
import com.graht.aichat.common.ResultCode;
import com.graht.aichat.config.AICredentialProperties;
import com.graht.aichat.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DefaultCredentialProvider implements CredentialProvider{
    @Resource
    private AICredentialProperties config;
    @Override
    public Credential getCredential(CredentialContext context) {
        return switch (context.getModelType()) {
            case OPENAI -> Credential.builder()
                    .apiKey(config.getModels().get(ModelType.OPENAI.getModelName()).getApiKey())
                    .baseUrl(config.getModels().get(ModelType.OPENAI.getModelName()).getBaseUrl())
                    .retryPolicyType(config.getModels().get(ModelType.OPENAI.getModelName()).getRetryPolicyType())
                    .build();
            case DEEPSEEK -> Credential.builder()
                    .apiKey(config.getModels().get(ModelType.DEEPSEEK.getModelName()).getApiKey())
                    .baseUrl(config.getModels().get(ModelType.DEEPSEEK.getModelName()).getBaseUrl())
                    .retryPolicyType(config.getModels().get(ModelType.OPENAI.getModelName()).getRetryPolicyType())
                    .build();
                    default -> throw new BusinessException(ResultCode.AI_CLIENT_NOT_FOUND);
        };
    }

}
