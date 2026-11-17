package pvz.control.commands;

import pvz.logic.GameModel;
import pvz.control.Level;
import pvz.view.GameView;
import pvz.view.Messages;

public class ResetCommand extends Command {
    private String levelName;
    private Long seed;
    
    public ResetCommand() {
        this.name = Messages.COMMAND_RESET_NAME;
        this.shortcut = Messages.COMMAND_RESET_SHORTCUT;
        this.details = Messages.COMMAND_RESET_DETAILS;
        this.help = Messages.COMMAND_RESET_HELP;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        if (this.levelName != null && this.seed != null) {
            try {
                Level level = Level.valueOf(this.levelName.toUpperCase());
                game.reset(level, this.seed);
            } catch (IllegalArgumentException e) {
                //ej: "reset ultra 5"
                view.showError("Invalid level"); 
            }
        } else {
            // Caso sin parámetros
            game.reset(); 
        }
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 1) {
            return new ResetCommand(); // Sin parámetros
        } 
        if (words.length == 3) {
            ResetCommand cmd = new ResetCommand();
            cmd.levelName = words[1];
            try {
                cmd.seed = Long.parseLong(words[2]);
                return cmd;
            } catch (NumberFormatException e) {
            	
                return null; // Semilla no válida
            }
        }
        return null;
    }
}