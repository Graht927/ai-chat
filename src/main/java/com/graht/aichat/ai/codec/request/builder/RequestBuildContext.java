package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.domain.ModelInfo;
import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.common.EndpointType;
import lombok.Builder;
import lombok.Getter;

/**
 * @author GRAHT
 */
@Getter
@Builder
public class RequestBuildContext<T> {
    private final T request;
    private final ModelInfo modelInfo;
    private final String requestId;
    private final String modelName;
    private final AIProvider provider;
    private final AICapability aiCapability;
    private final ProviderConfig providerConfig;
}
