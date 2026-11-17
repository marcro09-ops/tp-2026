package pvz.control.commands;

import pvz.logic.GameModel;
import pvz.logic.PlantFactory;
import pvz.logic.gameobjects.Plant;
import pvz.view.GameView;
import pvz.view.Messages;

public class ListPlantsCommand extends Command {
	
	public ListPlantsCommand() {
		this.name = Messages.COMMAND_LIST_PLANTS_NAME;
		this.shortcut = Messages.COMMAND_LIST_PLANTS_SHORTCUT;
		this.details = Messages.COMMAND_LIST_PLANTS_DETAILS;
		this.help = Messages.COMMAND_LIST_PLANTS_HELP;
    }
	
	@Override
	public void execute(GameModel game, GameView view) {
		StringBuilder listText = new StringBuilder();
       
        
        for (Plant plant : PlantFactory.getAvailablePlants()) {
            listText.append(plant.getDescription()).append(Messages.LINE_SEPARATOR);
        }
        view.showMessage(listText.toString().trim()+"\n");
    }

	@Override
	public Command create(String[] words) {
		if (words.length == 1) {
            return this;
        }
		return null;
	}

}

