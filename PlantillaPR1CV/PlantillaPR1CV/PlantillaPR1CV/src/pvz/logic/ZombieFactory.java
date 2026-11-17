	package pvz.logic;

import pvz.logic.gameobjects.BucketHeadZombie;

import pvz.logic.gameobjects.ExplosiveZombie;
import pvz.logic.gameobjects.SportyZombie;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.ZombieBasic;
import utils.Position;

// Importa aquí el resto de zombis cuando los crees (BucketHeadZombie, etc.)
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ZombieFactory {
    
    // Lista estática de prototipos
    private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
            new ZombieBasic(),
            new BucketHeadZombie(),
            new SportyZombie(),
            new ExplosiveZombie()
    );

    public static List<Zombie> getAvailableZombies() {
        return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
    }

    public static Zombie spawnZombie(String name, GameWorld game, Position pos) {
        if (name.equalsIgnoreCase("z") || name.equalsIgnoreCase("zombie")) {
            return new ZombieBasic(game, pos);
        } 
        else if (name.equalsIgnoreCase("bz") || name.equalsIgnoreCase("bucketheadzombie")) {
            return new BucketHeadZombie(game, pos);
        } 
        else if (name.equalsIgnoreCase("sz") || name.equalsIgnoreCase("sportyzombie")) {
            return new SportyZombie(game, pos);
        } 
        else if (name.equalsIgnoreCase("ez") || name.equalsIgnoreCase("explosivezombie")) {
            return new ExplosiveZombie(game, pos);
        }
        return null;
    }
}