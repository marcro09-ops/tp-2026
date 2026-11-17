package pvz.logic.actions;

import pvz.logic.GameItem;
import pvz.logic.GameWorld;
import utils.Position;

public class ExplosionAction implements GameAction {
    private Position center;
    private int damage;
    private boolean damagesPlants;

    public ExplosionAction(Position center, int damage, boolean damagesPlants) { //Preguntar esto
        this.center = center;
        this.damage = damage;
        this.damagesPlants = damagesPlants;
    }

    @Override
    public void execute(GameWorld game) {
        for (int c = -1; c <= 1; c++) {
            for (int r = -1; r <= 1; r++) {
                Position target = center.offset(c, r);
                
                if (game.isInsideBoard(target)) {
                    GameItem item = game.getGameItemInPosition(target);
                    if (item != null) {
                        if (damagesPlants) {
                            item.receiveZombieAttack(damage);
                        } else {
                            item.receivePlantAttack(damage);
                        }
                    }
                }
            }
        }
    }

	
}