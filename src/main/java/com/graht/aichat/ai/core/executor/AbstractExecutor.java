package com.graht.aichat.ai.core.executor;

import com.graht.aichat.ai.codec.request.builder.BuilderContextFactory;
import com.graht.aichat.ai.codec.request.builder.RequestBuildContext;
import com.graht.aichat.ai.codec.request.codec.RequestCodec;
import com.graht.aichat.ai.codec.request.codec.RequestCodecRegistry;
import com.graht.aichat.ai.codec.response.codec.ResponseCodec;
import com.graht.aichat.ai.codec.response.codec.ResponseCodecRegistry;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.domain.ProviderConfig;
import com.graht.aichat.ai.core.model.ProviderCapabilityKey;
import com.graht.aichat.ai.provider.ProviderFactory;
import com.graht.aichat.ai.transport.AIHttpClient;
import com.graht.aichat.ai.transport.AIHttpRequest;
import com.graht.aichat.ai.transport.AIHttpResponse;
import com.graht.aichat.infrastructure.retry.RetryExecutor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;

/**
 * @author GRAHT
 */
@Component
public abstract class AbstractExecutor<Req extends AIRequest,RES extends AIResponse> {
    private static final Logger log = LoggerFactory.getLogger(AbstractExecutor.class);
    @Resource
    protected RequestCodecRegistry requestCodecRegistry;

    @Resource
    protected ResponseCodecRegistry responseCodecRegistry;

    @Resource
    protected AIHttpClient aiHttpClient;

    @Resource
    protected RetryExecutor retryExecutor;

    @Resource
    protected ProviderFactory providerFactory;

    /**
     * 固定执行流程（模板方法）
     */
    public final RES execute(Req request) {
        log.debug(
                "[{}] Start Execute {}",
                request.getRequestId(),
                request.getAiCapability()
        );
        ProviderCapabilityKey key = request.supportType();

        ProviderConfig providerConfig = resolveProvider(request);

        RequestBuildContext<Req> context = BuilderContextFactory.from(providerConfig, request);

        AIHttpRequest httpRequest = encode(context, key);

        AIHttpResponse httpResponse =
                executeHttp(providerConfig, httpRequest);

        RES decode = decode(httpResponse, key);
        log.debug(
                "[{}] End Execute {}",
                request.getRequestId(),
                request.getAiCapability()
        );
        return decode;
    }

    /**
     * Provider -> Config
     */
    protected ProviderConfig resolveProvider(Req request) {
        return providerFactory.getProvider(request.getProvider());
    }

    /**
     * Request Encode
     */
    protected AIHttpRequest encode(
            RequestBuildContext<Req> context,
            ProviderCapabilityKey key) {

        RequestCodec<Req> codec =
                requestCodecRegistry.getCodec(key);

        return codec.encode(context);
    }

    /**
     * Http Execute
     */
    protected AIHttpResponse executeHttp(
            ProviderConfig providerConfig,
            AIHttpRequest request) {

        return retryExecutor.execute(
                providerConfig.getRetryPolicyType(),
                () -> aiHttpClient.execute(request)
        );
    }

    /**
     * Response Decode
     */
    protected RES decode(
            AIHttpResponse response,
            ProviderCapabilityKey key) {
        ResponseCodec<RES> responseCodec = responseCodecRegistry.getResponseCodec(key);
        return (RES) responseCodec.decode(response);
    }
}
