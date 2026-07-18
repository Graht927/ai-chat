package com.graht.aichat.ai.transport;

import com.graht.aichat.infrastructure.aop.AIHttp;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.ai.codec.request.RequestBuildFactory;
import com.graht.aichat.ai.codec.request.HttpRequestConverter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private RequestBuildFactory requestBuildFactory;
    @Resource
    private HttpRequestConverter httpRequestConverter;
    @Override
    @AIHttp
    public AIHttpResponse execute(HttpRequest request) {
        restClient.method(HttpMethod.valueOf(request.method()))
                .uri(request.uri())
                .headers(headers->{
                    HttpHeaders headers1 = request.headers();
                    for (Map.Entry<String, List<String>> entry : headers1.map().entrySet()) {
                        headers.add(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                })
                .body(request.bodyPublisher())
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

        return  null;
    }
}
