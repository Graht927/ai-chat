package com.graht.aichat.ai.codec.request.codec;

import com.graht.aichat.ai.codec.request.builder.DeepSeekRequestBuilder;
import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.codec.request.converter.DeepSeekChatRequestConverter;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.AICapability;
import com.graht.aichat.ai.core.model.AIProvider;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;

/**
 * @author GRAHT
 * 要做的是: 将请求参数转换为HttpRequest 统一形式
 */
@Component
public class DeepSeekChatRequestCodec implements RequestCodec<AIRequest> {
    @Resource
    private DeepSeekChatRequestConverter converter;
    @Resource
    private DeepSeekRequestBuilder builder;
    @Override
    public HttpRequest encode(RequestBuildContext<AIRequest> context) {
        String payload = converter.convert(context);
        return builder.build(context,payload);
    }

    @Override
    public ProviderCapabilityKey supportType(){
        return ProviderCapabilityKey.of(AIProvider.DEEPSEEK, AICapability.CHAT);
    }
}
