package com.fr.yncrea.isen.cir3.chess.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorLoggingAspect {

    @AfterThrowing(
            pointcut = "within(com.fr.yncrea.isen.cir3.chess..*)",
            throwing = "ex")
    public void logError(Exception ex) {
        System.err.println("❌ Excepción capturada por AOP: " + ex.getClass().getSimpleName());
    }
}
