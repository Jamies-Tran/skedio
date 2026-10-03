package com.skedio.corestarter.logs;

import com.skedio.corestarter.trace.TraceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class FunctionLogAspect {

    @Around("execution(* com.skedio.*.service..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long starter = System.currentTimeMillis();
        String method = joinPoint.getSignature().getName();
        String traceId = MDC.get(TraceContext.TRACE_ID);
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - starter;
            log.info(
                    "SUCCESS | traceId={} | method={} | duration={}",
                    traceId,
                    method,
                    duration
            );
            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - starter;
            log.info(
                    "FAIL | traceId={} | method={} | duration={}",
                    traceId,
                    method,
                    duration
            );
            throw new RuntimeException(e);
        }
    }
}
