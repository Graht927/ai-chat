package com.graht.aichat.ai.transport;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * @author GRAHT
 */
@Data
public class HttpResult {
    private int statusCode;
    private String body;
    private Map<String, List<String>> header;
    private long costTime;
    private String requestId;
}
