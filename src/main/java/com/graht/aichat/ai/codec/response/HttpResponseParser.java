package com.graht.aichat.ai.codec.response;

import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.transport.AIHttpResponse;

/**
 * @author GRAHT
 */


public interface HttpResponseParser<T> {
    AIResponse parse(AIHttpResponse response);
    AIProvider getProvider();
}
