package control.Commands;

import control.CommandParseException;
import logic.Game;


public class SuperCoinsCommand extends Command {
	
	private static final String name = "coins";
	private static final String shortcut = "c";
	private static final String details = "[c]oins";
	private static final String help = "add 1000 coins";
	
	public SuperCoinsCommand() {
		
		super(name, shortcut, details, help);
	}

	
	public boolean execute(Game game) {
		
		game.añadirSuperCoins();
		return true; 
		
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {	
		
		return this.parseNoParamsCommand(commandWords);
		
	}
} 
