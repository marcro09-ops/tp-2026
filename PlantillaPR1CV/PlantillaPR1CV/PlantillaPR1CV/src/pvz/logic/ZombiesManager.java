package pvz.logic;

import java.util.Random;


import pvz.control.Level;
import pvz.logic.gameobjects.*;
import utils.Position;

/**
 * Manages the full lifecycle of zombies for a game session.
 */
public class ZombiesManager {

    private Game game;
    private Level level;
    private Random rand;

    private int remainingZombies;
    private GameObjectContainer objects;
    private int zombiesAlive;

    public ZombiesManager(Game game, Level level, Random rand, GameObjectContainer o) {
        this.game = game;
        this.level = level;
        this.rand = rand;
        this.remainingZombies = level.getNumberOfZombies();
        this.objects = o;
    }

    /**
     * Checks if the game should add (if possible) a zombie to the game.
     * 
     * @return <code>true</code> if a zombie should be added to the game.
     */
    public boolean shouldAddZombie() {
        return rand.nextDouble() < level.getZombieFrequency();
    }
    
    /**
     * Return a random row within the board limits.
     * 
     * @return a random row.
     */
    private int randomZombieRow() {
        return rand.nextInt(Game.NUM_ROWS);
    }
    
    public boolean update() {
        return addZombie();
    }

    public boolean addZombie() {
    	// 1. ¿Toca sacar zombi?
        boolean probabilidad = shouldAddZombie();
        int row = randomZombieRow();
        Position zombiePosition = new Position(Game.NUM_COLS, row);
        // 2. Comprobamos condiciones básicas ANTES de calcular posiciones aleatorias
        boolean canAdd = this.remainingZombies > 0 && probabilidad && game.isEmpty(zombiePosition);
        if (canAdd) {
                String[] tipos = {"z", "bz", "sz", "ez"};
                String tipoElegido = tipos[rand.nextInt(tipos.length)];
                Zombie nuevoZombi = ZombieFactory.spawnZombie(tipoElegido, (GameWorld) game, zombiePosition);
                
                this.objects.add(nuevoZombi);
                this.remainingZombies--;
                this.zombiesAlive++;
        }
        return canAdd;
    }

    public void zombieDied() {
        this.zombiesAlive--;
    }
    
    public int getRemainingZombies() {
        return remainingZombies;
    }
    
    // revisa todos los objetos de el GameObjectContainer y mira cual de esllos está en la columna -1 (si es que le hay). 
    // No hace diferenciar entre plantas y zombies por que los únicos que pueden llegar a dicha columna son los zombies.
    public boolean hasZombieReachedEnd() {
        for(int i = 0; i < objects.size(); i++) {
            if(objects.getObject(i).getPosition().getCol() == -1) {
                return true;
            }
        }
        return false;
    }
    
    public boolean allZombiesDead() {
        return this.remainingZombies == 0 && this.zombiesAlive == 0;
    }
}