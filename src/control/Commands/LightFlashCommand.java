package control.Commands;

import control.CommandExecuteException;
import control.CommandParseException;
import logic.Game;


public class LightFlashCommand extends Command {
	
	private static final String name = "lightFlash";
	private static final String shortcut = "l";
	private static final String details = "[l]ight";
	private static final String help = "kills all the vampires";
	
	public LightFlashCommand() {		
		
		super(name, shortcut, details, help);
	}

	
	public boolean execute(Game game) throws CommandExecuteException {
		
		game.lightFlash();
		game.update();
		return true; 
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {	
		
		return this.parseNoParamsCommand(commandWords);
		
	}
} 
