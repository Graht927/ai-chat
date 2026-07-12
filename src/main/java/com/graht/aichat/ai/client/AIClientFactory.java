package com.graht.aichat.ai.client;

import com.graht.aichat.ai.model.ModelType;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class AIClientFactory {
    private final AIClientRegistry registry;
    public AIClientFactory(AIClientRegistry registry) {
        this.registry = registry;
    }
    public AIClient getClient(ModelType modelType) {
        return registry.getClient(modelType);
    }
}
