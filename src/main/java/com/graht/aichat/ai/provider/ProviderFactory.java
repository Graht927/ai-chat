package com.graht.aichat.ai.provider;

import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AIProvider;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class ProviderFactory {
    private final ProviderRegistry registry;
    public ProviderFactory(ProviderRegistry registry) {
        this.registry = registry;
    }
    public ProviderConfig getProvider(AIProvider provider) {
        return registry.getProvider(provider);
    }
}
