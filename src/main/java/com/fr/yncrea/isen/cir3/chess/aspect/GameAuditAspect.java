package com.fr.yncrea.isen.cir3.chess.aspect;

import com.fr.yncrea.isen.cir3.chess.domain.Game;
import com.fr.yncrea.isen.cir3.chess.repository.GameRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class GameAuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(GameAuditAspect.class);

    @Autowired
    private GameRepository gameRepository;

    // Este se ejecuta después del método EndGame
    @After("execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.EndGame(..))")
    public void auditGameEnd(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        if (args.length >= 3 && args[0] instanceof Long && args[1] instanceof String) {
            Long gameId = (Long) args[0];
            String winner = (String) args[1];

            Optional<Game> gameOpt = gameRepository.findById(gameId);
            if (gameOpt.isPresent()) {
                Game game = gameOpt.get();
                logger.info(" Auditoría: El juego ha terminado. Game ID: {}, Ganador: {}", gameId, winner);
            } else {
                logger.warn(" Auditoría: No se encontró el juego con ID: {}", gameId);
            }
        }
    }
}
