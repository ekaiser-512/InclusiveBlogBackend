package org.example.inclusiveblog.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* org.example.inclusiveblog.service..*.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method execution: " + joinPoint.getSignature().toShortString());

        try {
            // Proceed with the method invocation
            Object result = joinPoint.proceed();

            log.info("After method execution: " + joinPoint.getSignature().toShortString());
            return result;
        } catch (Throwable throwable) {
            log.error("Exception in method: " + joinPoint.getSignature().toShortString(), throwable);
            throw throwable; // Rethrow the exception
        }
    }
}

