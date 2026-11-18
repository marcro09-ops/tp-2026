package pvz.control.commands;

import pvz.logic.GameModel;


import pvz.logic.gameobjects.*;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;

public class CatchCommand extends Command{
	    private int col;
	    private int row;

	    public CatchCommand() {
	        this.name = Messages.COMMAND_CATCH_NAME;
	        this.shortcut = Messages.COMMAND_CATCH_SHORTCUT;
	        this.details = Messages.COMMAND_CATCH_DETAILS;
	        this.help = Messages.COMMAND_CATCH_HELP;
	    }

	    @Override
	    public void execute(GameModel game, GameView view) {
	        Position pos = new Position(this.col, this.row);
	        GameObject sun=game.canCatchSun(pos);
	        if (sun==null) {
	            view.showError(String.format(Messages.NO_CATCHABLE_IN_POSITION, col, row));
	            return; 
	        }
	        
	       
	       
           game.setAlreadyCatch(true);
           game.removeDeadSun(sun);
           game.newCaughtSun();
          
          view.showGame();
          
           
	    }

	    @Override
	    public Command create(String[] words) {
	        if (words.length == 3) {
	            CatchCommand cmd = new CatchCommand();
	            try {
	                cmd.col = Integer.parseInt(words[1]);
	                cmd.row = Integer.parseInt(words[2]);
	                return cmd;
	            } catch (NumberFormatException e) {
	                return null;
	            }
	        }
	        return null;
	    }
}
