package com.fr.yncrea.isen.cir3.chess.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FriendRequestNotificationAspect {

    @After("execution(* com.fr.yncrea.isen.cir3.chess.controller.FriendRequestController.sendFriendRequest(..))")
    public void notifyFriendRequest(JoinPoint joinPoint) {
        System.out.println("Notificación: Se envio una solicitud de amistad.");
    }
}
