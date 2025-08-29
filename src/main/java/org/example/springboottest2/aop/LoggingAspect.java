package org.example.springboottest2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//    @Around("execution(* org.example.springboottest2.services..*(..))")
//    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
//        String method = joinPoint.getSignature().toShortString();
//        long start = System.currentTimeMillis();
//
//        log.info("{} - INPUT: {}", method, Arrays.toString(joinPoint.getArgs()));
//
//        try {
//            Object result = joinPoint.proceed();
//            long time = System.currentTimeMillis() - start;
//
//            log.info("{} - OUTPUT: {} - TIME: {}ms", method, result, time);
//            return result;
//
//        } catch (Exception e) {
//            long time = System.currentTimeMillis() - start;
//            log.error("{} - ERROR: {} - TIME: {}ms", method, e.getMessage(), time);
//            throw e;
//        }
//    }

    @Around("execution(* org.example.springboottest2.controllers..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint);
    }

    @Around("execution(* org.example.springboottest2.services..*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint);
    }

    private Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();

        log.info("[INPUT] {} : {}", method, Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - start;

            log.info("[OUTPUT] {} : {} - TIME: {}ms", method, result, time);
            return result;

        } catch (Exception e) {
            long time = System.currentTimeMillis() - start;
            log.error("[ERROR] {} : {} - TIME: {}ms", method, e.getMessage(), time);
            throw e;
        }
    }
}