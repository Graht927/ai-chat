package com.graht.aichat.infrastructure.retry;

import com.graht.aichat.infrastructure.retry.RetryContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author GRAHT
 */
@Component
public class RetryListenerManager{

    private static final Logger log = LoggerFactory.getLogger(RetryListenerManager.class);
    private final List<RetryListener> listeners;

    public RetryListenerManager(List<RetryListener> listeners) {
        this.listeners = listeners;
    }
    public void onStart(RetryContext context) {
        notify(listener -> listener.onStart(context));
    }
    public void onComplete(RetryContext context) {
        notify(listener -> listener.onComplete(context));
    }
    public void onRetry(RetryContext context) {
        notify(listener -> listener.onRetry(context));
    }
    public void onAttemptFailed(RetryContext context) {
        notify(listener -> listener.onAttemptFailed(context));
    }
    private void notify(Consumer<RetryListener> consumer){
        listeners.forEach(listeners->{
            try {
                consumer.accept(listeners);
            }catch (Exception e){
                log.error("RetryListener error",e);
            }
        });
    }

}
