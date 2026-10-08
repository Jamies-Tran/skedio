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

    @Around("execution(* com.skedio..service..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long starter = System.currentTimeMillis();
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String classPathMethod = "%s.%s".formatted(className, method);
        String traceId = MDC.get(TraceContext.TRACE_ID);
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - starter;
            log.info(
                    "[{}] method={} | duration={} | status=success",
                    traceId,
                    classPathMethod,
                    duration
            );
            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - starter;
            log.error(
                    "[{}] method={} | duration={} | status=fail",
                    traceId,
                    classPathMethod,
                    duration
            );
            throw e;
        }
    }
}
