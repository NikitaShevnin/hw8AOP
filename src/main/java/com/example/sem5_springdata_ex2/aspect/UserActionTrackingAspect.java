package com.example.sem5_springdata_ex2.aspect;

import com.example.sem5_springdata_ex2.repository.TrackUserAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class UserActionTrackingAspect {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserActionTrackingAspect.class);

    @Around("@annotation(com.example.sem5springdataex2.annotation.TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String methodName = method.getName();
        String targetClassName = method.getDeclaringClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        TrackUserAction trackUserAction = method.getAnnotation(TrackUserAction.class);
        String actionDescription = trackUserAction.value().isEmpty() ? methodName : trackUserAction.value();

        logger.info("User action: {} in {} with arguments: {}");

        Object result = joinPoint.proceed();
        return result;
    }
}

