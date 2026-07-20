package com.graht.aichat.ai.codec.response.converter;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;

/**
 * @author GRAHT
 */

public interface ResponseConverter<T> {
    AIResponse convert(T response);
    ProviderCapabilityKey supportType();
    Class<T> resType();


}
