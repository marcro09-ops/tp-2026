package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.*;
import utils.Position;

public class WallNut extends Plant {
    public static final int COST = 50;
    private static final int INITIAL_HEALTH = 10;
    


    public WallNut(GameWorld game, Position position) {
        super(game, position, INITIAL_HEALTH);
       
    }

    public WallNut() {}

    @Override
    public int cost() {
        return COST;
    }

    @Override
    public void update() {
        // No hace nada, es un muro
    }


    @Override
    public String shortName() {
        return Messages.WALL_NUT_SYMBOL;
    }

    @Override
    public String longName() {
        return Messages.WALL_NUT_NAME;
    }

    @Override
    public String getDescription() {
        return String.format(Messages.PLANT_DESCRIPTION, 
                Messages.WALL_NUT_NAME_SHORTCUT, COST, 0,  INITIAL_HEALTH);
    }
}