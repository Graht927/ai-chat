package com.graht.aichat.ai.codec.response.parser;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.transport.AIHttpResponse;

/**
 * @author GRAHT
 */


public interface HttpResponseParser<T> {
    T parse(AIHttpResponse response);
    AIProvider getProvider();
}
