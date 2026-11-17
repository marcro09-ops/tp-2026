package pvz.logic.gameobjects;
import pvz.view.Messages;



import utils.Position;
import pvz.logic.GameItem;
import pvz.logic.*;

public class Peashooter extends Plant{
    public static final int COST = 50;
    private static final int DAMAGE = 1;
    private static final int INITIAL_HEALTH = 3;

    
   public Peashooter(){}

    public Peashooter(GameWorld game, Position position) {
    	super(game, position, INITIAL_HEALTH);
       
    }
    
  
    @Override
    public String getDescription() {
        // [P]eashooter: cost='50' suncoins, damage='1', endurance='3'
    	 return String.format(
         		Messages.PEASHOOTER_DESCRIPTION,
         		COST,
         		DAMAGE,
         		INITIAL_HEALTH);
    }
    
    @Override
    public int cost() {
    	return COST;
    }
   
    @Override
    public void update() {
    	if (this.isAlive()) {
    	    this.shoot();
    	   }
    }

    private void shoot() {
        boolean zombieHit = false;
        int i = this.position.getCol() + 1;
        while (i < Game.NUM_COLS && !zombieHit) {
            Position p = new Position(i, this.position.getRow());
            GameItem item = game.getGameItemInPosition(p);
            if(item!=null) {
              zombieHit = item.receivePlantAttack( DAMAGE);
            }
            	i++;
        }
    }
    
    
@Override
    public String shortName() {
        return Messages.PEASHOOTER_SYMBOL;
    }
@Override
    public String longName() {
        return Messages.PEASHOOTER_NAME;
    }
}

