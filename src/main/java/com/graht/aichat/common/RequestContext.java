package com.graht.aichat.common;

import java.util.UUID;

/**
 * @author GRAHT
 */

public class RequestContext {
    private static final ThreadLocal<String> REQUEST_ID  = new ThreadLocal<>();
    private RequestContext() {
    }
    public static String getRequestId() {
        return REQUEST_ID.get();
    }
    public static void setRequestId(String requestId) {
        RequestContext.REQUEST_ID.set(requestId);
    }
    public static void clear() {
        REQUEST_ID.remove();
    }
}
