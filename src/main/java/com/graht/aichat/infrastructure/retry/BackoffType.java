package com.graht.aichat.infrastructure.retry;

/**
 * @author GRAHT
 */

public enum BackoffType {
    FIXED,
    EXPONENTIAL,
    EXPONENTIAL_JITTER
}