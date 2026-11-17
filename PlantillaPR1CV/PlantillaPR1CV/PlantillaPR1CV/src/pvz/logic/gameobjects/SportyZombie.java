package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class SportyZombie extends Zombie {

    public SportyZombie(GameWorld g, Position position) {
        super(g, position, 2, 1); // 2 de vida, 1 de velocidad
    }

    public SportyZombie() {}

    @Override
    public String shortName() { return Messages.SPORTY_ZOMBIE_SYMBOL; }

    @Override
    public String longName() { return Messages.SPORTY_ZOMBIE_SYMBOL; }
}
