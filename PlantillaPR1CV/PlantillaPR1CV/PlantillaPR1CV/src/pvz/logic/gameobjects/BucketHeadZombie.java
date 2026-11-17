package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(GameWorld g, Position position) {
        super(g, position, 8, 4); // 8 de vida, 4 de velocidad
    }

    public BucketHeadZombie() {}

    @Override
    public String shortName() { return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL; }

    @Override
    public String longName() { return Messages.BUCKET_HEAD_ZOMBIE_NAME; }
}