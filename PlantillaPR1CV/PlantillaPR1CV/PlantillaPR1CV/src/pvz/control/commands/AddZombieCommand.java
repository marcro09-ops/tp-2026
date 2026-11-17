package pvz.control.commands;

import pvz.logic.GameModel;
import pvz.logic.GameWorld;
import pvz.logic.ZombieFactory;
import pvz.logic.gameobjects.Zombie;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;

public class AddZombieCommand extends Command {
    
    private String zombieName; 
    private int col;
    private int row;

    public AddZombieCommand() {
        this.name = Messages.COMMAND_ADD_ZOMBIE_NAME;
        this.shortcut = Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
        this.details = Messages.COMMAND_ADD_ZOMBIE_DETAILS;
        this.help = Messages.COMMAND_ADD_ZOMBIE_HELP;
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

        Zombie zombie = ZombieFactory.spawnZombie(this.zombieName, (GameWorld) game, pos);

        if (zombie == null) {
            view.showError(Messages.INVALID_GAME_OBJECT);
            return;
        }

        game.forceAddGameObject(zombie);
        game.update();
        view.showGame();
    }

    @Override
    public Command create(String[] words) {
        if (words.length == 4) {
            AddZombieCommand cmd = new AddZombieCommand();
            // Guardamos directamente el texto (ej: "bz") sin parsear
            cmd.zombieName = words[1]; 
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