package control.Commands;

import control.CommandParseException;
import logic.Game;

public class SaveCommand extends Command{

	private String fileName;
	private static final String name = "save";
	private static final String shortcut = "s";
	private static final String details = "[S]ave <filename>";
	private static final String help = "Save the state of the game to a file.";
	
	public SaveCommand() {
		super(name, shortcut, details, help);

	}
	
	public boolean execute(Game game) 
	{
		game.saveGame(fileName);
		
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException
	{				
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) {
				throw new CommandParseException("[ERROR]: Command " + name + " : " + incorrectNumberOfArgsMsg);
			}
			fileName = commandWords[1] + ".dat";
			
			return this;
		}
		
		return null;
	}
	
	
}
