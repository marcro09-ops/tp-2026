package pvz.logic.gameobjects;

import pvz.logic.GameItem;
import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public abstract class Zombie extends GameObject {

    protected static final int DAMAGE = 1;

    protected int health;
    protected int initialHealth;
    protected int speed;
    protected int cyclesToNextMovement;

    public Zombie(GameWorld g, Position position, int initialHealth, int speed) {
        super(g, position);
        this.initialHealth = initialHealth;
        this.health = initialHealth;
        this.speed = speed;
        this.cyclesToNextMovement = speed;
    }

    public Zombie() {
    }

    @Override
    public String getIcon() {
        return String.format(Messages.GAME_OBJECT_ICON, shortName(), this.health);
    }

    @Override
    public String getDescription() {
        return String.format(
                Messages.ZOMBIE_DESCRIPTION,
                this.longName(),
                this.speed,
                DAMAGE,
                this.initialHealth
        );
    }

    @Override
    public boolean receiveZombieAttack(int damage) {
        return false;
    }

    @Override 
    public boolean receivePlantAttack(int damage) {
        this.health -= damage;
        return true;
    }

    @Override
    public void update() {
        GameItem item = game.getGameItemInPosition(position.left());
        
        if (item == null) {
            if (cyclesToNextMovement == 1) {
                move();
                cyclesToNextMovement = speed;
            } else {
                cyclesToNextMovement--;
            }
        } else {
            item.receiveZombieAttack(DAMAGE);
        }
    }

    private void move() {
        this.position = position.left();
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
        game.zombieDied();
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
    
    @Override
    public boolean catchSun() {
    	return false;
    }
    
}