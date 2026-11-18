package pvz.control.commands;

import pvz.logic.GameModel;
import pvz.logic.ZombieFactory;
import pvz.logic.gameobjects.Zombie;
import pvz.view.GameView;
import pvz.view.Messages;

public class ListZombiesCommand extends Command {

    public ListZombiesCommand() {
        this.name = Messages.COMMAND_LIST_ZOMBIES_NAME;
        this.shortcut = Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;
        this.details = Messages.COMMAND_LIST_ZOMBIES_DETAILS;
        this.help = Messages.COMMAND_LIST_ZOMBIES_HELP;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        StringBuilder listText = new StringBuilder();
        listText.append("Available zombies:");
        for (Zombie zombie : ZombieFactory.getAvailableZombies()) {
            listText.append(zombie.getDescription()).append(Messages.LINE_SEPARATOR);
        }
        view.showMessage(listText.toString().trim()+"\n");
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 1) {
            return new ListZombiesCommand();
        }
        return null;
    }
}