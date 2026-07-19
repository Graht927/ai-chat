package com.graht.aichat.ai.codec.response.parser;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class HttpResponseParserRegistry implements BeanPostProcessor {
    private final Map<AIProvider, HttpResponseParser<?>> httpResponseParserMap = new EnumMap<>(AIProvider.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof HttpResponseParser<?> parser) {
            AIProvider provider = parser.getProvider();

            HttpResponseParser<?> old =
                    httpResponseParserMap.putIfAbsent(
                            provider,
                            parser
                    );

            if(old != null){
                throw new AIException(
                        AIErrorCode.AI_INIT_PARSER_ERROR,
                        "Duplicate HttpResponseParser for provider: " + provider
                );
            }
        }
        return bean;
    }
    public HttpResponseParser getParser(AIProvider provider) {
        HttpResponseParser httpResponseParser = httpResponseParserMap.get(provider);
        if (httpResponseParser == null) {
            throw new AIException(AIErrorCode.AI_PARSER_NOT_FOUND, "No HttpResponseParser found for provider: " + provider);
        }
        return httpResponseParser;
    }
}
