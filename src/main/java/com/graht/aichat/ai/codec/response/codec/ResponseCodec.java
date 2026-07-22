package com.graht.aichat.ai.codec.response.codec;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.transport.AIHttpResponse;

/**
 * @author GRAHT
 */
public interface ResponseCodec<T> {
    T decode(AIHttpResponse response);
    ProviderCapabilityKey supportType();
}
