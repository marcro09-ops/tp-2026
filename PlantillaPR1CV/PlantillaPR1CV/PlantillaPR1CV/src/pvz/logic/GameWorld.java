package pvz.logic;

import utils.Position;
import pvz.logic.gameobjects.*;

public interface GameWorld {
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 8;

    boolean isEmpty(Position p);
    boolean isInsideBoard(Position p);
    void generateCoins(int amount);
    void buyWithCoins(int cost);
    void zombieDied();
    GameObject getGameItemInPosition(Position p);
    void createSunFromSunflower();
    boolean canCatchSun(Position p);
    boolean addGameObject(Plant plantType);
    void forceAddGameObject(GameObject o);
    void eliminateSun(Position pos);
    void pushAction(pvz.logic.actions.GameAction action);
}