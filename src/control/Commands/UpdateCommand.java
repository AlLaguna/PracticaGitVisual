package control.Commands;

import control.CommandParseException;
import logic.Game;

public class UpdateCommand extends Command{
	
	private static final String name = "none";
	private static final String shortcut = "n";
	private static final String details = "[n]one | []";
	private static final String help = "update";
	
	public UpdateCommand() 
	{
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) 
	{
		game.update();
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException
	{

		if(commandWords[0].isEmpty() || commandWords[0].equals(shortcut) ||  commandWords[0].equals(name))
		{
			if(commandWords.length == 0 || commandWords.length == 1)
			{
				commandWords[0] = "n";
				return this;
			}
			else
			{
				throw new CommandParseException("[ERROR]: Command " + name + " : " + incorrectNumberOfArgsMsg);
			}
		}
		
		return null;
	}
}



