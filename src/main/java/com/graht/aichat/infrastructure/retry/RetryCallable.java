package com.graht.aichat.infrastructure.retry;

@FunctionalInterface
public interface RetryCallable<T>{

    T call() throws Exception;

}