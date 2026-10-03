package com.skedio.corestarter.trace;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.slf4j.MDC;

@Getter
public class TraceContext {
    public static final String TRACE_ID = "traceId";
    public static final String TRACE_HEADER = "x-traceId";

    public static void clear() {
        MDC.remove(TRACE_ID);
    }

    public static  void set(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }
}
