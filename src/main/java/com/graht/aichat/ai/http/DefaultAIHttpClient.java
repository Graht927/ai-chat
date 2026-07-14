package com.graht.aichat.ai.http;

import com.graht.aichat.ai.domain.AIHttpResponse;
import com.graht.aichat.ai.dto.AIHttpRequest;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

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
    public AIHttpResponse execute(AIHttpRequest request) {
        long start = System.currentTimeMillis();
        try {
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
                        return AIHttpResponse.builder()
                                .statusCode(res.getStatusCode().value())
                                .body(body)
                                .costTime(System.currentTimeMillis() - start)
                                .build();
                    });
        }catch (Exception e){
            throw new AIException(AIErrorCode.PROVIDER_ERROR);
        }
    }
}
