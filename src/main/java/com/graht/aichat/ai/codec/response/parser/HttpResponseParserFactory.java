package com.graht.aichat.ai.codec.response.parser;

import com.graht.aichat.ai.core.model.AIProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class HttpResponseParserFactory {
    @Resource
    private HttpResponseParserRegistry parserRegister;
    public HttpResponseParser getParser(AIProvider  provider){
        return parserRegister.getParser(provider);
    }
}
