package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.RetryContext;

/**
 * @author GRAHT
 */

public interface RetryListener {


    default void onStart(RetryContext context){

    }
    default void onAttemptFailed(RetryContext context){

    }
    default void onRetry(RetryContext context){

    }
    default void onComplete(RetryContext context){

    }

}
