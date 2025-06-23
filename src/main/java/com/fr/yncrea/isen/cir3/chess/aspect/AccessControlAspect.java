package com.fr.yncrea.isen.cir3.chess.aspect;

import com.fr.yncrea.isen.cir3.chess.exception.UnauthorizedAccessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Aspect
@Component
public class AccessControlAspect {

    @Before("@annotation(com.fr.yncrea.isen.cir3.chess.annotation.RequireLogin)")
    public void verificarSesion(JoinPoint jp) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            throw new UnauthorizedAccessException("Usuario no autenticado.");
        }

        String username = auth.getName();
        System.out.println(" Usuario autenticado: " + username);
    }
}

