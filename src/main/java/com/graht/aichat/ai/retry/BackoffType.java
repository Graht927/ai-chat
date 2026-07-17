package com.graht.aichat.ai.retry;

/**
 * @author GRAHT
 */

public enum BackoffType {
    FIXED,
    EXPONENTIAL,
    EXPONENTIAL_JITTER
}