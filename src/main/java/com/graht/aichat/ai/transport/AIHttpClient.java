package com.graht.aichat.ai.transport;

import com.graht.aichat.ai.transport.AIHttpResponse;

import java.net.http.HttpRequest;

/**
 * @author GRAHT
 */
public interface AIHttpClient {
    AIHttpResponse execute(AIHttpRequest request);
}
