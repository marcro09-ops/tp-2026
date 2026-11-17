package pvz.logic.gameobjects;

import utils.Position;

import pvz.logic.*;
import pvz.view.Messages;

public abstract class Plant extends GameObject{
	
	private int health;
	
	 Plant(GameWorld game, Position p, int h) {
	 super(game, p);
	 this.health=h;
	 }
	 
	 Plant(){}
	 
	 @Override
	 public String getIcon() {
	    	return String.format(
	        		Messages.GAME_OBJECT_ICON,
	        		shortName(),
	        		this.health);
	    }
	 
	 @Override
	 public boolean isAlive() {
	        return this.health > 0;
	    }
	 
	 @Override
	    public void onEnter() {
	    	if(game!=null)
	        	game.buyWithCoins(cost());
	    }
	 
     @Override
	    public void onExit() {
	    	
	    }
     
     @Override
     public boolean receiveZombieAttack(int damage) {
     	this.health -= damage;
     	return true;
     }
     
     @Override
     public boolean receivePlantAttack(int damage) {
     	
     	return false;
     }

	 public void killPlant(){//Metodo para autoeliminar una planta (en principio solo para Cherry-Bomb)
		 this.health = 0;
	 }
    abstract public int cost();
    
    @Override
    public boolean catchSun() {
    	return false;
    }
    

}
