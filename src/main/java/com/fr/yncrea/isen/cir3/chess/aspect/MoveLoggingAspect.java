package com.fr.yncrea.isen.cir3.chess.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MoveLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(MoveLoggingAspect.class);

    @AfterReturning(
            pointcut = "execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.moveOnVoidCell(..)) || " +
                    "execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.moveOnAnyPawn(..)) || " +
                    "execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.priseEnPassant(..))",
            returning = "result"
    )
    public void logMove(Object result) {
        logger.info("Una jugada ha sido realizada. Redirigiendo a: {}", result);
    }
}
