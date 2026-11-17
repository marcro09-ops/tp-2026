package pvz.control;

import pvz.control.commands.Command;
import pvz.logic.Game;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;

/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>Owns the game loop: reads a line from stdin, parses it into a
 * command, delegates execution, and triggers a board reprint via
 * {@link pvz.view.GameView}. It holds no game state of its own.
 */
public class Controller {

    private final Game game;
    private final GameView view;

    // Si modificaste el main como te dije antes, puedes usar (Game game, GameView view)
    // Si no, lo dejamos así instanciando el Printer internamente.
    public Controller(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    // Constructor alternativo por si en tu main solo le pasabas el Game
    public Controller(Game game) {
        this.game = game;
        this.view = new GamePrinter(game);
    }

    public void run() {
     
        this.view.showGame();

        // 2. Bucle principal
        while (!this.game.hasGameFinished()) {
            String[] words = this.view.getPrompt(); 
        
            Command command = Command.parse(words);

            if (command != null) {
                command.execute(this.game, this.view);
                // Si tras ejecutar el comando (y quizás actualizar el ciclo) el juego sigue activo, repintamos
                
            } else {
                // Si Command.parse devolvió null, es un error de sintaxis o no existe
                this.view.showError(Messages.UNKNOWN_COMMAND);
            }
        }
        
        // 3. Al terminar el bucle, se muestra el mensaje de victoria/derrota
        this.view.showEndMessage();
    }
}