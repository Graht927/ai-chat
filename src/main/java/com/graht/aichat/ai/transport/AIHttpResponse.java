package com.graht.aichat.ai.transport;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author GRAHT
 */
@Data
@Builder
public class AIHttpResponse {
    private Integer statusCode;
    private Map<String, String> headers;
    private String body;
    private Long costTime;
}
