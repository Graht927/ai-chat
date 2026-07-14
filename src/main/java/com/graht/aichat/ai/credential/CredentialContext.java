package com.graht.aichat.ai.credential;

import com.graht.aichat.ai.model.ModelType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CredentialContext {

    /**
     * 当前用户
     */
    private Long userId;


    /**
     * 当前租户
     */
    private String tenantId;


    /**
     * 请求模型
     */
    private ModelType modelType;

}