package com.graht.aichat.ai.codec.request;

import com.graht.aichat.ai.core.model.AIProvider;
import java.net.http.HttpRequest;
/**
 * @author GRAHT
 */


public interface HttpRequestBuilder<T> {
    HttpRequest build(RequestBuildContext<T> context);
    AIProvider provider();
}
