package control.Commands;

import control.CommandParseException;
import logic.Game;

public class HelpCommand extends Command
{
	private static final String name = "help";
	private static final String shortcut = "h";
	private static final String details = "[h]elp";
	private static final String help = "show this help";
	
	public HelpCommand() 
	{	
		super(name, shortcut, details, help);	
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		
		return this.parseNoParamsCommand(commandWords);
	}
	
	
	public boolean execute(Game game) {
		
		System.out.print(CommandGenerator.commandHelp());
		return false;
	}

}






