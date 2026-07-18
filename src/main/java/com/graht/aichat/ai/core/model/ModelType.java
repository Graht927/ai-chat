package com.graht.aichat.ai.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GRAHT
 */


public enum ModelType {

    QWEN("qwen","1"),
    OPENAI("openai","1"),
    DEEPSEEK("deepseek","deepseek-chat"),;
    private final String modelName;
    private final String modelVersion;
    ModelType(String modelName, String modelVersion){
        this.modelName = modelName;
        this.modelVersion = modelVersion;
    }
    public String getModelName() {
        return modelName;
    }
    public String getModelVersion() {
        return modelVersion;
    }
    private static final Map<String, ModelType> CACHE = new HashMap<>();
    static {
        for (ModelType type : values()) {
            CACHE.put(type.getModelName() + ":" + type.getModelVersion(), type);
        }
    }
    public static ModelType resolve(String modelName, String modelVersion){
        if (modelName == null || modelVersion == null) {
            return null;
        }
        return CACHE.get(modelName +":"+ modelVersion);
    }
    @Override
    public String toString() {
        return "ModelType{" +
                "modelName='" + modelName + '\'' +
                ", modelVersion='" + modelVersion + '\'' +
                '}';
    }
}
