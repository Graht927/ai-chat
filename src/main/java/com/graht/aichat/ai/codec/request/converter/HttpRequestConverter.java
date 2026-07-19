package com.graht.aichat.ai.codec.request.converter;

import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.core.model.AIProvider;

/**
 * @author GRAHT
 */

public interface HttpRequestConverter {
    String convert(RequestBuildContext context);
    AIProvider provider();
}
