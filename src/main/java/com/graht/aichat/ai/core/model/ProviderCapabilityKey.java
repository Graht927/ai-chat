package com.graht.aichat.ai.core.model;

public record ProviderCapabilityKey(AIProvider provider, AICapability capability) {

    public static ProviderCapabilityKey of(AIProvider provider, AICapability capability) {
        return new ProviderCapabilityKey(provider, capability);
    }
    @Override
    public String toString() {
        return "ProviderCapabilityKey{" +
                "provider=" + provider +
                ", capability=" + capability +
                '}';
    }
}
