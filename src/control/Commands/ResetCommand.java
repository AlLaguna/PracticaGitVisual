package control.Commands;

import control.CommandParseException;
import logic.Game;

public class ResetCommand extends Command{
	
	private static final String name = "reset";
	private static final String shortcut = "r";
	private static final String details = "[r]eset";
	private static final String help = "reset game";
	
	public ResetCommand() {
		
		super(name, shortcut, details, help);
		
	}
	
	public boolean execute(Game game) {
		
		game.reset();
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		
		return this.parseNoParamsCommand(commandWords);
	}
}
