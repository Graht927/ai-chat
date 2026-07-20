package com.graht.aichat.ai.codec.request.builder;

import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;

import java.net.http.HttpRequest;
/**
 * @author GRAHT
 */


public interface HttpRequestBuilder {
    HttpRequest build(RequestBuildContext<?> context,String payload);
    AIProvider provider();
}
