package com.graht.aichat.ai.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @author GRAHT
 */
@Data
@Builder
public class AIHttpRequest {
    private String url;
    private HttpMethod method;
    private Map<String, String> headers;
    private Object body;
}
