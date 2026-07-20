package com.graht.aichat.ai.codec.response.codec;

import com.graht.aichat.ai.codec.response.converter.DeepSeekChatResponseConverter;
import com.graht.aichat.ai.codec.response.parser.DeepSeekHttpResponseParser;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.provider.deepseek.DeepSeekResponse;
import com.graht.aichat.ai.transport.AIHttpResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author GRAHT
 */
@Component
public class DeepSeekChatResponseCodec implements ResponseCodec{
    @Resource
    private DeepSeekHttpResponseParser deepSeekHttpResponseParser;
    @Resource
    private DeepSeekChatResponseConverter deepSeekResponseConverter;
    @Override
    public AIResponse decode(AIHttpResponse response) {
        DeepSeekResponse deepSeekResponse = deepSeekHttpResponseParser.parse(response);
        return deepSeekResponseConverter.convert(deepSeekResponse);
    }

    @Override
    public ProviderCapabilityKey supportType() {
        return ProviderCapabilityKey.of(AIProvider.DEEPSEEK, AICapability.CHAT);
    }


}
