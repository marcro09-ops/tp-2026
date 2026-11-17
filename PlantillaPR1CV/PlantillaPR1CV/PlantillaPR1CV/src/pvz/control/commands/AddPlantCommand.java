package pvz.control.commands;

import pvz.view.Messages;
import pvz.control.commands.*;
import pvz.logic.*;
import pvz.logic.gameobjects.*;
import pvz.view.*;
import utils.Position;

public class AddPlantCommand extends Command{
	private String plantName;
    private int col;
    private int row;

    public AddPlantCommand() {
    	this.name = Messages.COMMAND_ADD_PLANT_NAME;
		this.shortcut = Messages.COMMAND_ADD_PLANT_SHORTCUT;
		this.details = Messages.COMMAND_ADD_PLANT_DETAILS;
		this.help = Messages.COMMAND_ADD_PLANT_HELP;
    }

    @Override
    public void execute(GameModel game, GameView view) {
       Position pos = new Position(this.col, this.row);
    	
        if (!game.isInsideBoard(pos)) {
            view.showError(Messages.INVALID_POSITION);
            return; 
        }
        if (!game.isEmpty(pos)) {
            view.showError(Messages.INVALID_POSITION); 
            return; 
        }
        Plant plant = PlantFactory.spawnPlant(this.plantName, game, pos);
        if (plant == null) {
            view.showError(Messages.INVALID_GAME_OBJECT);
            return;
        }
        boolean added = game.addGameObject(plant); 
        
        if (added) {
            game.update();
            view.showGame();
        } else {
        	
            view.showError(Messages.NOT_ENOUGH_COINS);
        }
 
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 4) {
            AddPlantCommand cmd = new AddPlantCommand();
            cmd.plantName = words[1];
            try {
                cmd.col = Integer.parseInt(words[2]);
                cmd.row = Integer.parseInt(words[3]);
                return cmd;
            } catch (NumberFormatException e) {
                return null; // La fila o columna no son números
            }
        }
        return null;
    }
}
