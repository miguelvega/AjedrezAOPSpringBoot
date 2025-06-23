package com.fr.yncrea.isen.cir3.chess.aspect;

import com.fr.yncrea.isen.cir3.chess.domain.Game;
import com.fr.yncrea.isen.cir3.chess.repository.GameRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class MovingTimeAspect {

    @Autowired
    private GameRepository gameRepository;

    @After("execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.moveOnVoidCell(..)) || " +
           "execution(* com.fr.yncrea.isen.cir3.chess.controller.GameController.moveOnAnyPawn(..))")
    public void calcularTiempoEntreJugadas(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Long gameId = null;

        for (Object arg : args) {
            if (arg instanceof Long) {
                gameId = (Long) arg;
                break;
            }
            try {
                gameId = Long.valueOf(arg.toString());
                break;
            } catch (Exception ignored) {}
        }

        if (gameId != null) {
            Optional<Game> optionalGame = gameRepository.findById(gameId);
            if (optionalGame.isPresent()) {
                Game game = optionalGame.get();
                long ahora = System.currentTimeMillis();

                Long anterior = game.getLastMoveTimestamp();
                if (anterior != null) {
                    long diferencia = ahora - anterior;
                    System.out.println(">>> Tiempo entre jugadas: " + diferencia + " ms");
                } else {
                    System.out.println(">>> Primera jugada registrada.");
                }

                game.setLastMoveTimestamp(ahora);
                gameRepository.save(game);
            }
        } else {
            System.out.println("No se encontró un argumento válido para gameId");
        }
    }
}

