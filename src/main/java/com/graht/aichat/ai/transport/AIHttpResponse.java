package com.graht.aichat.ai.transport;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author GRAHT
 */
@Data
@Builder
public class AIHttpResponse {
    private int statusCode;
    private Map<String, List<String>> headers;
    private String body;
    private Long costTimeMillis;
}
