package com.graht.aichat.ai.transport;

import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import com.graht.aichat.infrastructure.aop.AIHttp;
import com.graht.aichat.ai.codec.request.builder.RequestBuildFactory;
import com.graht.aichat.ai.codec.request.converter.HttpRequestConverter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpRequest;
import java.util.function.Consumer;

/**
 * @author GRAHT
 */
@Component
public class DefaultAIHttpClient implements AIHttpClient{

    private static final Logger log = LoggerFactory.getLogger(DefaultAIHttpClient.class);
    private final RestClient restClient;

    public DefaultAIHttpClient(@Qualifier("restClient") RestClient restClient) {
        this.restClient = restClient;
    }
    @Resource
    private RequestBuildFactory requestBuildFactory;
    @Resource
    private HttpRequestConverter httpRequestConverter;
    @Override
    @AIHttp
    public AIHttpResponse execute(AIHttpRequest request) {
        try {
            AIHttpResponse exchange = restClient.method(request.getMethod())
                    .uri(request.getUrl())
                    .headers(header->{
                        header.addAll(request.getHeaders());
                    })
                    .body(request.getBody())
                    .exchange((req, res) -> AIHttpResponse.builder()
                            .statusCode(res.getStatusCode().value())
                            .body(res.bodyTo(String.class))
                            .headers(res.getHeaders())
                            .build());
            log.info(exchange.getBody().toString());
            return exchange;
        }catch (Exception e){
            throw new AIException(AIErrorCode.AI_REQUEST_ERROR,e.getMessage());
        }
    }
}
