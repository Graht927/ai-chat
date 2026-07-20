package com.graht.aichat.ai.codec.response.parser;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.common.AIErrorCode;
import com.graht.aichat.exception.AIException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class HttpResponseParserRegistry implements BeanPostProcessor {
    private final Map<ProviderCapabilityKey, HttpResponseParser<?>> httpResponseParserMap = new HashMap<>();
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof HttpResponseParser<?> parser) {

            HttpResponseParser<?> old =
                    httpResponseParserMap.putIfAbsent(
                            parser.supportType(),
                            parser
                    );

            if(old != null){
                throw new AIException(
                        AIErrorCode.AI_INIT_PARSER_ERROR,
                        "Duplicate HttpResponseParser for ProviderCapability: " + parser.supportType()
                );
            }
        }
        return bean;
    }
    public HttpResponseParser getParser(ProviderCapabilityKey key) {
        HttpResponseParser httpResponseParser = httpResponseParserMap.get(key);
        if (httpResponseParser == null) {
            throw new AIException(AIErrorCode.AI_PARSER_NOT_FOUND, "No HttpResponseParser found for ProviderCapability: " + key);
        }
        return httpResponseParser;
    }
}
