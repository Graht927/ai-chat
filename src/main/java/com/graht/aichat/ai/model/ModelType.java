package com.graht.aichat.ai.model;

/**
 * @author GRAHT
 */


public enum ModelType {
    QWEN("qwen","1"),
    OPENAI("openai","1"),
    DEEPSEEK("deepseek","1"),;
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
    @Override
    public String toString() {
        return "ModelType{" +
                "modelName='" + modelName + '\'' +
                ", modelVersion='" + modelVersion + '\'' +
                '}';
    }
}
