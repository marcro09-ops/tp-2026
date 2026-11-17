package pvz.control.commands;

import pvz.logic.GameModel;

import pvz.logic.GameWorld;
import pvz.logic.PlantFactory;
import pvz.logic.gameobjects.Plant;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;

public class AddPlantCheatCommand extends Command {
    private String plantName;
    private int col;
    private int row;

    public AddPlantCheatCommand() {
        this.name = Messages.COMMAND_ADD_PLANT_CHEAT_NAME;
        this.shortcut = Messages.COMMAND_ADD_PLANT_CHEAT_SHORTCUT;
        this.details = Messages.COMMAND_ADD_PLANT_CHEAT_DETAILS;
        this.help = Messages.COMMAND_ADD_PLANT_CHEAT_HELP;
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
        Plant plant = PlantFactory.spawnPlant(this.plantName, (GameWorld) game, pos);

        if (plant == null) {
            view.showError(Messages.INVALID_GAME_OBJECT);
            return;
        }
        game.forceAddGameObject(plant);
        game.update();
        view.showGame();
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 4) {
            AddPlantCheatCommand cmd = new AddPlantCheatCommand();
            cmd.plantName = words[1];
            try {
                cmd.col = Integer.parseInt(words[2]);
                cmd.row = Integer.parseInt(words[3]);
                return cmd;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        
        return null;
    }
}