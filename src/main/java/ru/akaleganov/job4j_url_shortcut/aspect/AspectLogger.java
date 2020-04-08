package ru.akaleganov.job4j_url_shortcut.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * аспект для логирования сервисов
 */
@Aspect
public class AspectLogger {
    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class);

    @Pointcut("execution(* ru.akaleganov.job4j_url_shortcut.service.*.*(..))")
    private void allLogMethod() {
    }

    @Before("allLogMethod()")
    private void beforeLog(JoinPoint joinPoint) {
        LOGGER.debug("Выполнился метод:" + joinPoint.getTarget().getClass().getSimpleName() + " "
                + joinPoint.getSignature().getName());
        String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.debug("Входящие параметры: " + joinPoint.toString() + ", args=[" + args + "]");
    }

    @AfterReturning(pointcut = "allLogMethod()", returning = "ret")
    private void afterLog(Object ret) {
        LOGGER.debug("Возвращаемое значение: " + ret);
    }

}
