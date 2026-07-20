package com.graht.aichat.ai.codec.response.parser;

import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class HttpResponseParserFactory {
    @Resource
    private HttpResponseParserRegistry parserRegister;
    public HttpResponseParser getParser(ProviderCapabilityKey key){
        return parserRegister.getParser(key);
    }
}
