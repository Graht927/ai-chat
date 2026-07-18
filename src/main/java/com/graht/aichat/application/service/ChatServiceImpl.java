package com.graht.aichat.application.service;

import com.graht.aichat.ai.core.client.AIClient;
import com.graht.aichat.ai.core.client.AIClientFactory;
import com.graht.aichat.ai.core.domain.AIResponse;
import com.graht.aichat.ai.core.domain.AIResult;
import com.graht.aichat.ai.core.domain.AIRequest;
import com.graht.aichat.ai.core.model.ModelType;
import com.graht.aichat.common.RequestContext;
import com.graht.aichat.common.ResultCode;
import com.graht.aichat.application.dto.ChatRequest;
import com.graht.aichat.exception.BusinessException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author GRAHT
 */
@Service
public class ChatServiceImpl implements ChatService{
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    @Resource
    private AIClientFactory clientFactory;

    @Override
    public AIResponse chat(ChatRequest request) {
        ModelType modelType = ModelType.resolve(request.getModelName(), request.getModelVersion());
        if(modelType == null) {
            throw new BusinessException(ResultCode.INVALID_PARAMETER, "Invalid model type");
        }
        AIClient client = clientFactory.getClient(modelType);
        AIResult aiResult = client.chat(AIRequest.builder()
                .prompt(request.getMessage())
                .requestId(RequestContext.getRequestId())
                        .model(modelType.getModelVersion())
                .build());
        return AIResponse.builder()
                .answer(aiResult.getAnswer())
                .provider(client.getModelType().getModelName())
                .model(client.getModelType().getModelName())
                .modelVersion(client.getModelType().getModelVersion())
                .status("success")
                .build();
    }
}
