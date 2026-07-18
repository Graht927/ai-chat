package com.graht.aichat.ai.provider;

import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.config.AIProviderProperties;
import com.graht.aichat.exception.AIException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class ProviderRegistry {
    @Resource
    private AIProviderProperties providerProperties;
    public ProviderConfig getProvider(AIProvider  provider){
        ProviderConfig providerConfig = providerProperties.getProviders().get(provider);
        if (providerConfig == null) {
            throw new AIException(AIErrorCode.AI_PROVIDER_NOT_FOUND);
        }
        return providerConfig;
    }
}
