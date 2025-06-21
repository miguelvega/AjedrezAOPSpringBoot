package com.fr.yncrea.isen.cir3.chess.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessControlAspect {

    @Before("execution(public * com.fr.yncrea.isen.cir3.chess.controller.UserController.*(..))")
    public void verificarPermisos(JoinPoint jp) {
        // Aquí podrías verificar roles, tokens, etc.
        System.out.println("🔐 Acceso a: " + jp.getSignature());
        // throw new AccessDeniedException("No autorizado"); // si deseas bloquear
    }
}
