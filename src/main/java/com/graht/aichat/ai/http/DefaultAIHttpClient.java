package com.graht.aichat.ai.http;

import com.graht.aichat.ai.annotation.AIHttp;
import com.graht.aichat.ai.domain.AIHttpResponse;
import com.graht.aichat.ai.dto.AIHttpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class DefaultAIHttpClient implements AIHttpClient{

    private final RestClient restClient;

    public DefaultAIHttpClient(@Qualifier("restClient") RestClient restClient) {
        this.restClient = restClient;
    }
    @Override
    @AIHttp
    public AIHttpResponse execute(AIHttpRequest request) {
          return  restClient.method(request.getMethod())
                    .uri(request.getUrl())
                    .headers(headers -> {
                        if (request.getHeaders() != null) {
                            request.getHeaders().forEach(headers::add);
                        }
                    })
                    .body(request.getBody())
                    .exchange((req,res)->{
                        String body = res.bodyTo(String.class);
                        Map<String, String> headerMap = new HashMap<>();
                        res.getHeaders().forEach((name, values) -> {
                            headerMap.put(name, String.join(",", values));
                        });
                        return AIHttpResponse.builder()
                                .statusCode(res.getStatusCode().value())
                                .body(body)
                                .headers(headerMap)
                                .build();
                    });
    }
}
