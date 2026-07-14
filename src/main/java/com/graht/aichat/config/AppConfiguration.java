package com.graht.aichat.config;

import com.graht.aichat.common.RequestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * @author GRAHT
 */
@Configuration
public class AppConfiguration {
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestInterceptor((request,body,execution)->{
                    request.getHeaders().add("X-Request-Id", RequestContext.getRequestId());
                    return execution.execute(request,body);
                })
                .build();
    }
}
