package com.graht.aichat.ai.codec.request.codec;

import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;

import java.net.http.HttpRequest;

/**
 * @author GRAHT
 */

public interface RequestCodec {
    HttpRequest encode(RequestBuildContext<?> context);
    ProviderCapabilityKey supportType();
}
