package com.graht.aichat.ai.codec.request.codec;

import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;

import java.net.http.HttpRequest;

/**
 * @author GRAHT
 */

public interface RequestCodec<T extends AIRequest> {
    HttpRequest encode(RequestBuildContext<T> context);
    ProviderCapabilityKey supportType();
}
