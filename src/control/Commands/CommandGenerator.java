package control.Commands;

import control.CommandParseException;

public class CommandGenerator
{
	 protected static final String unknownCommandMsg = "Unknown Command";
	 
	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new GarlicPushCommand(),
		new LightFlashCommand(),
		new AddBloodBankCommand(),
		new SuperCoinsCommand(),
		new AddVampireCommand(),
		new SaveCommand(),
		new SerializeCommand()
		};

	public static Command parseCommand(String[] commandWords)  throws CommandParseException
	{	
		
		for(Command comando: availableCommands)
		{
			if(comando.parse(commandWords) != null) 
			{	
				return comando;
			}
		}

		throw new CommandParseException("[ERROR]: " + unknownCommandMsg);


	}
	
	public static String commandHelp() 
	{
		String comando = "Available commands:\n";
	
		for(Command c: availableCommands)
		{
			comando =  comando + c.helpText();
		}
		return comando;
	}
}
