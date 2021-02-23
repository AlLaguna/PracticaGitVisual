package control.Commands;

import control.CommandExecuteException;
import control.CommandParseException;
import control.NotEnoughCoinsException;
import logic.Game;

public class AddBloodBankCommand extends Command{
	
	private static final String name = "bank";
	private static final String shortcut = "b";
	private static final String details = "[b]ank <x> <y> <z>";
	private static final String help = "add a blood bank with cost z in position x, y";
	public static final String invalidPositionMsg = String.format("[ERROR]: Invalid position");
	private int[] posicion;
	
	public AddBloodBankCommand() {
		
		super(name, shortcut, details, help);
		posicion = new int[3];
	}

	
	public boolean execute(Game game) throws CommandExecuteException 
	{ 
		if(game.añadirBB(posicion[1], posicion[0], posicion[2]))
		{
			game.update();
			return true;
		}
		else
		{
			return false;
		}
	}
	

	public Command parse(String[] commandWords) throws CommandParseException
	{				
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 4) 
			{
				throw new CommandParseException("[ERROR]: " + incorrectNumberOfArgsMsg + " for add" + name + " command: " + details);
			}
			
			if(Character.isDigit(commandWords[1].charAt(0)) && Character.isDigit(commandWords[2].charAt(0)) && Character.isDigit(commandWords[3].charAt(0)))
			{
				posicion[0] = Integer.parseInt(commandWords[1]);
				posicion[1] = Integer.parseInt(commandWords[2]);
				posicion[2] = Integer.parseInt(commandWords[3]);
				return this;
			}
			else
			{
				throw new CommandParseException("[ERROR]: Unvalid argument for add" + name + " command, number expected: " + details);
			}
		}
		
		return null;
	}
}

