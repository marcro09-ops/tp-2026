package pvz.logic.gameobjects; 

import pvz.logic.*;

import java.util.Random;
import utils.Position;
import pvz.view.Messages;

public class Sunflower extends Plant{

    // Constantes estáticas
    public static final int COST = 20;
    private static final int INITIAL_HEALTH = 1;
    private static final int DAMAGE = 0;
    
    private static final int COOLDOWN = 3;

    // Atributos
   
    private int cyclesSinceLastCoinGeneration;

    // Constructor
    public Sunflower(){}
    
    public Sunflower(GameWorld g, Position position) {
    	super(g, position, INITIAL_HEALTH);
        
        this.cyclesSinceLastCoinGeneration=0;
        
    }

    @Override
    public String getDescription() {
        
        return String.format(
        		Messages.SUNFLOWER_DESCRIPTION,
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
    	
        if(cyclesSinceLastCoinGeneration==COOLDOWN) {//Da soles después  de tres ciclos, no al tercero
        	game.createSunFromSunflower();
        	cyclesSinceLastCoinGeneration=1;
        	
        }
        else{
        	cyclesSinceLastCoinGeneration++;
        }
        
    }

    @Override
    public String shortName() {
    	return Messages.SUNFLOWER_SYMBOL;
    }
    
    @Override
    public String longName() {
    	return Messages.SUNFLOWER_NAME;
    }

   
}