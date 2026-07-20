package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.ProviderConfig;

/**
 * @author GRAHT
 */
public class BuilderContextFactory {
    public static  <R extends AIRequest> RequestBuildContext<R> from(
            ProviderConfig provider,
            R request) {
        return RequestBuildContext.<R>builder()
                .request(request)
                .provider(request.getProvider())
                .providerConfig(provider)
                .modelInfo(provider.requireModel(request.getModel()))
                .requestId(request.getRequestId())
                .aiCapability(request.getAiCapability())
                .build();
    }
}
