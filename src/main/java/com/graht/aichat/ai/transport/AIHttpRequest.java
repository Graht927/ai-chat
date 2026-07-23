package com.graht.aichat.ai.transport;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * AI内部HTTP请求模型
 *
 * @author GRAHT
 */
@Data
@Builder
public class AIHttpRequest {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 请求头
     */
    private HttpHeaders headers;

    /**
     * 请求体
     */
    private String body;
}