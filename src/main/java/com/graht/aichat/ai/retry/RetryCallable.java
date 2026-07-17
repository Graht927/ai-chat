package com.graht.aichat.ai.retry;

@FunctionalInterface
public interface RetryCallable<T>{

    T call() throws Exception;

}