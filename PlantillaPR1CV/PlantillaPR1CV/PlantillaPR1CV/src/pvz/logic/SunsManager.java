package pvz.logic;

import java.util.Random;



import pvz.logic.gameobjects.*;
import utils.Position;


public class SunsManager {
	
	public static final int SUN_GENERATION_CYCLES=5;
	
	private Game game;
    private Random rand;
   
    public SunsManager(Game game, Random rand) {
        this.game = game;
        this.rand = rand;
   
    }

    public void update() {
        if (game.getCycles() % SUN_GENERATION_CYCLES == 0 && game.getCycles()!=0) {
            	spawnRandomSun();
        }
    }
    
    public void spawnRandomSun() {
    	int row = rand.nextInt(GameWorld.NUM_ROWS);
    	int col = rand.nextInt(GameWorld.NUM_COLS);
        
        Position randomPos = new Position(col, row);
        game.forceAddGameObject(new Sun(game, randomPos));
        game.newGeneratedSun();
    }
    
}
