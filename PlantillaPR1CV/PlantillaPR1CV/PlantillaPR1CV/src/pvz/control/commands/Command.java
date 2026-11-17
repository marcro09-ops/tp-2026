package pvz.control.commands;
import java.util.Arrays;
import java.util.List;
import pvz.logic.Game;
import pvz.logic.GameModel;
import pvz.view.GameView;
public abstract class Command {
	
    private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
        new HelpCommand(),
        new ExitCommand(),
        new NoneCommand(),
        new ListPlantsCommand(),
        new ResetCommand(),
        new AddPlantCommand(),
        new AddPlantCheatCommand(),
        new AddZombieCommand(),
        new CatchCommand(),
        new ListZombiesCommand()
        
    );

    protected String name = "";
    protected String shortcut = "";
    protected String details = "";
    protected String help = "";

    public abstract void execute(GameModel game, GameView view);
    public abstract Command create(String[] words);

    
    public boolean matchCommand(String word) {
        return this.shortcut.equalsIgnoreCase(word) || this.name.equalsIgnoreCase(word);
    }

    public static Command parse(String[] commandWords) {
        if (commandWords == null || commandWords.length == 0) return null;
        
        for (Command command : AVAILABLE_COMMANDS) {
            if (command.matchCommand(commandWords[0])) {
                return command.create(commandWords);
            }
        }
        return null;
    }
}