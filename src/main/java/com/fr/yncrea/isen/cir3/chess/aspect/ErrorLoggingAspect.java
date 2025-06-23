package com.fr.yncrea.isen.cir3.chess.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class ErrorLoggingAspect {

    @AfterThrowing(
            pointcut = "within(com.fr.yncrea.isen.cir3.chess.controller..*)",
            throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        String method = joinPoint.getSignature().toShortString();
        String errorType = ex.getClass().getSimpleName();
        String message = ex.getMessage();
        String timestamp = LocalDateTime.now().toString();

        System.err.println(" [" + timestamp + "] Error en " + method);
        System.err.println("   Tipo: " + errorType);
        System.err.println("   Mensaje: " + message);
}
}