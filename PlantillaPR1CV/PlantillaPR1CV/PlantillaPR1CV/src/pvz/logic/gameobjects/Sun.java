package pvz.logic.gameobjects;

import pvz.logic.gameobjects.*;
import pvz.logic.*;
import utils.Position;
import pvz.view.Messages;

public class Sun extends GameObject{

	public static final int INITIAL_CYCLES=11; //En el ciclo de su creación ya se actualiza. Pero al crearse debe tener 10 ciclos de vida
	private static final int GENERATED_SUNS = 10;
	
	private int remCycles; //Remaining cycles
	
	public Sun(GameWorld game, Position p) {
		super(game, p);
		remCycles=INITIAL_CYCLES;
	}
	
	@Override
	public String getIcon() {
		return String.format(Messages.SUN_ICON, this.remCycles);
	};
	
	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	};
	
	@Override
	public String shortName() {
		return Messages.SUN_SYMBOL;
	};
	
	@Override
	public String longName() {
		return Messages.SUN_DESCRIPTION;
	};
	
	@Override
	public boolean isAlive() {
		return remCycles>0;
	};
	
	@Override
	public void onEnter() {};
	
	@Override
	public void onExit() {
		game.generateCoins(GENERATED_SUNS);
	};

	
	@Override
	public void update() {
		remCycles--;
	};
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	};
	
	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	};
	
	@Override
    public boolean catchSun() {
		this.remCycles=0;
    	return true;
    }
	
	
	
}
