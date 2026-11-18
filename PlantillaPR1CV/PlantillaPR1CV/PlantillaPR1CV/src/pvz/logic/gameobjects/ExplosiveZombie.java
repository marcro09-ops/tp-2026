package pvz.logic.gameobjects;


import pvz.logic.GameWorld;
import pvz.logic.actions.ExplosionAction;
import pvz.view.Messages;
import utils.Position;

public class ExplosiveZombie extends Zombie {

    public ExplosiveZombie(GameWorld g, Position position) {
        super(g, position, 5, 2); // 5 de vida, 2 de velocidad
    }

    public ExplosiveZombie() {}

    @Override
    public void onExit() {
    	super.onExit(); 
        game.pushAction(new ExplosionAction(this.position, 3, true));
    }
   
    @Override
    public String shortName() { return Messages.EXPLOSIVE_ZOMBIE_SYMBOL; }

    @Override
    public String longName() { return Messages.EXPLOSIVE_ZOMBIE_NAME; }
}