package control.Commands;

import control.CommandParseException;
import logic.Game;


 public class ExitCommand extends Command {
	 
 	private static final String name = "exit";
	private static final String shortcut = "e";
	private static final String details = "[e]xit";
	private static final String help = "exit game";
	
	public ExitCommand() {
		
		super(name, shortcut, details, help);
		
	}
	
	public boolean execute(Game game) {
		game.exit();
		return false;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		
		return this.parseNoParamsCommand(commandWords);
	}
}
 








 











