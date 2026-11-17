package pvz.logic;



import pvz.control.Level;


public interface GameModel extends GameWorld, GameStatus{
    void reset();
    void update();
    void quit();
    void reset(Level level, Long seed);
     
    
}