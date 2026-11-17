package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import utils.Position;

public class ZombieBasic extends Zombie {

    public ZombieBasic(GameWorld g, Position position) {
        super(g, position, 5, 2);
    }

    public ZombieBasic() {}

    @Override
    public String shortName() { return "Z"; }

    @Override
    public String longName() { return "Zombie"; }
}