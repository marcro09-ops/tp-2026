package pvz.control.commands;

import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class NoneCommand extends Command {

    public NoneCommand() {
    	this.name = Messages.COMMAND_UPDATE_NAME;
		this.shortcut = Messages.COMMAND_UPDATE_SHORTCUT;
		this.details = Messages.COMMAND_UPDATE_DETAILS;
		this.help = Messages.COMMAND_UPDATE_HELP;
    }

    @Override
    public boolean matchCommand(String word) {
        return this.shortcut.equalsIgnoreCase(word) || 
               this.name.equalsIgnoreCase(word) || 
               word.equals("");
    }

    @Override
    public void execute(GameModel game, GameView view) {
        game.update();
        view.showGame();
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 1 || (words.length == 1 && words[0].equals(""))) {
            return this;
        }
        return null;
    }
}
