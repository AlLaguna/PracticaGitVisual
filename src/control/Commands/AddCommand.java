package control.Commands;

import control.CommandExecuteException;
import control.CommandParseException;
import logic.Game;

public class AddCommand extends Command{
	
	private static final String name = "add";
	private static final String shortcut = "a";
	private static final String details = "[a]dd <x> <y>";
	private static final String help = "add a slayer in position x, y";
	public static final String invalidPositionMsg = String.format("[ERROR]: Invalid position");
	private int[] posicion;
	
	public AddCommand() {
		
		super(name, shortcut, details, help);
		posicion = new int[2];
	}

	
	public boolean execute(Game game) throws CommandExecuteException 
	{ 
			if(game.añadirSlayer(posicion[1], posicion[0]))
			{
				game.update();
				return true;
			}
			else
			{
				System.out.print("[ERROR]: Failed to add slayer");
				return false;
			}
	}
	

	public Command parse(String[] commandWords) throws CommandParseException
	{				
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 3) {
				throw new CommandParseException("[ERROR]: " + incorrectNumberOfArgsMsg + " for " + name + " command: " + details);
			}
			
			if(Character.isDigit(commandWords[1].charAt(0)) && Character.isDigit(commandWords[2].charAt(0)))
			{
				posicion[0] = Integer.parseInt(commandWords[1]);
				posicion[1] = Integer.parseInt(commandWords[2]);
				return this;
			}
			else
			{
				throw new CommandParseException("[ERROR]: Unvalid argument for " + name + "slayer command, number expected: " + details);
			}
		}
		
		return null;
	}
}


