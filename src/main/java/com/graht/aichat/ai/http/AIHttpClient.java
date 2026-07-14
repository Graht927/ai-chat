package com.graht.aichat.ai.http;

import com.graht.aichat.ai.domain.AIHttpResponse;
import com.graht.aichat.ai.dto.AIHttpRequest;

/**
 * @author GRAHT
 */

public interface AIHttpClient {
    AIHttpResponse execute(AIHttpRequest request);
}
