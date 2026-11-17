package pvz.logic;

import utils.Position;

public interface GameStatus {
    int getCycles();
    int getCoins();
    int getRemainingZombies();
    void setAlreadyCatch(boolean b);
    void newGeneratedSun(); 
    void newCaughtSun();
    int getGeneratedSuns();
    int getCaughtSuns();
    String positionToString(Position position);
    boolean playerWins();
    boolean playerQuits();
    
}