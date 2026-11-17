package pvz.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import pvz.logic.actions.GameAction;
import pvz.logic.gameobjects.*;
import pvz.control.Level;
import utils.Position;

public class Game implements GameStatus, GameWorld, GameModel {
    public static final int INITIAL_COINS = 50;

    private int cycles;
    private int coins;
    private Long longSeed;
    private Random seed;
    private boolean playerQuit;
    private int generatedSuns;
    private int caughtSuns;
    private boolean alreadyCatch; //Para saber si en un ciclo ya se ha cogido un sol
   
    
    private Level level;
    
    
    private GameObjectContainer objects;
    private ZombiesManager zombiesManager;
    private SunsManager sunsManager;
    private Deque<GameAction> actions;
    

    public Game(Long seed, Level level) {
        this.longSeed = seed;
        this.level = level;
        this.reset();
        this.actions = new ArrayDeque<>();
    }

    @Override
    public void reset() {
        this.seed = new Random(this.longSeed);
        this.cycles = 0;
        this.coins = INITIAL_COINS;
        this.objects=new GameObjectContainer();
        this.sunsManager=new SunsManager(this, this.seed);
        this.zombiesManager = new ZombiesManager(this, this.level, this.seed, this.objects);
        this.playerQuit = false;
        this.caughtSuns = 0;
        this.generatedSuns=0;
        this.alreadyCatch=false;
        this.actions = new ArrayDeque<>();
    }
    
	public void setAlreadyCatch(boolean alreadyCatch) {
		this.alreadyCatch = alreadyCatch;
	}

	@Override
    public void reset(Level level, Long seed) {
        this.level = level;
        this.longSeed = seed;
        this.reset();
    }

	@Override
	public void update() {
	    this.alreadyCatch = false;
	    executePendingActions();
	    this.zombiesManager.update();           // 1º sorteo de zombi (fila, tipo, probabilidad)
	    this.sunsManager.update();              // 2º sol del cielo
	    this.objects.update();                  // 3º objetos (los girasoles generan soles)
	    this.zombiesManager.addPendingZombie(); // 4º el zombi nuevo entra al tablero
	    boolean deadRemoved = true;
	    while (deadRemoved || arePendingActionsLeft()) {
	        deadRemoved = this.objects.removeDead();
	        executePendingActions();
	    }
	    this.cycles++;
	}

    @Override
    public void quit() {
        this.playerQuit = true;
    }
    
    @Override
    public void forceAddGameObject(GameObject o ) {
        this.objects.add(o);
    }

    @Override
    public boolean addGameObject(Plant p) {
  
    	if(p.cost()<=this.coins) {
    		objects.add(p);
    		p.onEnter();
    		return true;
    	}
    	
        return false;
    }

    @Override
    public int getCycles() { return this.cycles; }

    @Override
    public int getCoins() { return this.coins; }

    @Override
    public int getRemainingZombies() { return this.zombiesManager.getRemainingZombies(); }
    
    @Override
    public void zombieDied() {
        this.zombiesManager.zombieDied();
    }

    @Override
    public String positionToString(Position position) {
        String icon = this.objects.iconInPosition(position);
        if(icon != null) {
        	return icon;
        }
        return "";
     }

    @Override
    public boolean isEmpty(Position p) {
        return this.objects.isEmpty(p);
    }

    @Override
    public boolean isInsideBoard(Position p) {
        return p.getCol() >= 0 && p.getCol() < NUM_COLS && 
               p.getRow() >= 0 && p.getRow() < NUM_ROWS;
    }

    @Override
    public boolean playerWins() {
        return zombiesManager.allZombiesDead();
    }

    @Override
    public boolean playerQuits() {
        return this.playerQuit;
    }

    public boolean hasGameFinished() {
        return this.playerQuit || this.playerWins() || this.zombiesManager.hasZombieReachedEnd();
    }
    
    @Override 
    public void generateCoins(int amount) {
    	this.coins += amount;
    }
    
    @Override
    public void buyWithCoins(int cost) {
    	this.coins -= cost;
    }
    
    @Override
    public GameObject getGameItemInPosition(Position position) {
    	return objects.getObject(objects.getIndex(position));
    }
    
    @Override
    public void newGeneratedSun() {
    	generatedSuns++;
    }
    
    @Override 
    public void newCaughtSun() {
    	caughtSuns++;
    } 
    
    @Override 
    public int getGeneratedSuns() {
    	return this.generatedSuns;
    }
    
    @Override 
    public int getCaughtSuns() {
    	return this.caughtSuns;
    }
    
    @Override
    public void createSunFromSunflower() {
    	this.sunsManager.spawnRandomSun();
    }

	@Override 
	public boolean canCatchSun(Position p) { //Comprueba si en una posicion hay un sol u otro objecto distinto
		GameItem item= getGameItemInPosition(p);
		if(item==null || !item.catchSun() || this.alreadyCatch) {
			return false;
		}
		else {
			return true;
		}
		}
	
	@Override 
	public void eliminateSun(Position p) { //Solo se ejecuta después de comprobar que el objeto es un sol
		objects.removeFromIndex(objects.getIndex(p));
	}
	
    

    @Override
    public void pushAction(GameAction gameAction) {
        this.actions.addLast(gameAction);
    }

    private void executePendingActions() {
        while (!this.actions.isEmpty()) {
            GameAction action = this.actions.removeLast();
            action.execute(this);
        }
    }

    private boolean arePendingActionsLeft() {
        return !this.actions.isEmpty();
    }
}