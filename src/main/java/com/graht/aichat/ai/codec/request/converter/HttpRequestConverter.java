package com.graht.aichat.ai.codec.request.converter;

import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;

/**
 * @author GRAHT
 */

public interface HttpRequestConverter<T> {
    String convert(RequestBuildContext<?> context);
    ProviderCapabilityKey supportType();
}
