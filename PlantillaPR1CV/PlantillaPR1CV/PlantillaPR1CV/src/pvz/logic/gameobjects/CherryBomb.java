package pvz.logic.gameobjects;



import pvz.logic.GameWorld;
import pvz.logic.actions.ExplosionAction;
import pvz.view.Messages;
import utils.Position;

public class CherryBomb extends Plant {
    public static final int COST = 50;
    private static final int INITIAL_HEALTH = 2;
    private static final int DAMAGE = 10;

   
    private int cyclesToExplode;

    public CherryBomb(GameWorld game, Position position) {
        super(game, position, INITIAL_HEALTH);
        
        this.cyclesToExplode = 3;
    }
    
    public CherryBomb() {} // Para la factoría

    @Override
    public int cost() {
        return COST;
    }

    @Override
    public void update() {
        if (this.isAlive()) {
            this.cyclesToExplode--;
            if (this.cyclesToExplode == 0) {
                explode();
            }
        }
    }

    private void explode() {
        game.pushAction(new ExplosionAction(this.position, 10, false));
        this.receiveZombieAttack(INITIAL_HEALTH);
    }

    @Override
    public String shortName() {
        
        if(this.cyclesToExplode<=1) {
        	return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
        }
        else {
        	return Messages.CHERRY_BOMB_SYMBOL;
        }
    }

    @Override
    public String longName() {
        return Messages.CHERRY_BOMB_NAME;
    }

    @Override
    public String getDescription() {
        return String.format(Messages.PLANT_DESCRIPTION, 
                Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, INITIAL_HEALTH);
    }
}