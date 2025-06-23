# Versions used for the project

- postgresql 12.2
- java 11

# Before launching the project

```
sudo -u postgres psql



CREATE DATABASE springchess;

CREATE ROLE springchess WITH LOGIN PASSWORD 'springchess';

GRANT ALL PRIVILEGES ON DATABASE springchess TO springchess;

\c springchess


GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO springchess;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO springchess;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO springchess;


ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT ALL ON TABLES TO springchess;
```



## Proposed AOP Aspects for the Chess Project (Spring Boot)

1. **Logging de Jugadas**  
   Registra cada vez que un jugador realiza un movimiento.  
   - **Anotación:** `@AfterReturning`  
   - **Método objetivo:** `GameController.makeMove(...)`

2. **Auditoría de Partidas**  
   Guarda información cuando una partida finaliza.  
   - **Anotación:** `@After`  
   - **Método objetivo:** `Game.setEnd(...)`

3. **Medición de Tiempo**  
   Calcula cuánto demora procesar una jugada.  
   - **Anotación:** `@Around`  
   - **Método objetivo:** `GameController.makeMove(...)`

4. **Control de Acceso**  
   Verifica permisos antes de acceder a funciones del usuario.  
   - **Anotación:** `@Before`  
   - **Método objetivo:** Métodos de `UserController`

5. **Manejo Centralizado de Errores**  
   Captura excepciones como `FiguraCodeNotInRange`.  
   - **Anotación:** `@AfterThrowing`  
   - **Ámbito:** Todos los métodos del paquete `chess`

6. **Notificación de Solicitud de Amistad**  
   Detecta y registra cuándo se envía una solicitud.  
   - **Anotación:** `@After`  
   - **Método objetivo:** `FriendRequestController.sendRequest(...)`

# launch project

- Ejecutar el proyecto usando tu IDE o la línea de comandos de Maven
