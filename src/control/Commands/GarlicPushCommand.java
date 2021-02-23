package control.Commands;

import control.CommandExecuteException;
import control.CommandParseException;
import logic.Game;


public class GarlicPushCommand extends Command {
	
	private static final String name = "garlicPush";
	private static final String shortcut = "g";
	private static final String details = "[g]arlic";
	private static final String help = "pushes back vampires";
	
	
	public GarlicPushCommand() {
		
		super(name, shortcut, details, help);
		
	}

	
	public boolean execute(Game game) throws CommandExecuteException 
	{
		game.garlicPush();
		game.update();
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		
		return this.parseNoParamsCommand(commandWords);
	}

}
