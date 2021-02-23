package control.Commands;

import control.CommandExecuteException;
import control.CommandParseException;
import control.UnvalidTypeException;
import logic.Game;

public class AddVampireCommand extends Command {

	private static final String name = "vampire";
	private static final String shortcut = "v";
	private static final String details = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	private static final String help = "add a vampire in position x, y";
	
	private int[] posicion;
	private String tipo;
	
	public AddVampireCommand() {
		
		super(name, shortcut, details, help);
		posicion = new int[2];
	}

	
	public boolean execute(Game game) throws CommandExecuteException 
	{ 
		game.añadirVampiroManual(tipo, posicion[1], posicion[0]);
		return true;
	}
	

	public Command parse(String[] commandWords) throws CommandParseException
	{				
		if (matchCommandName(commandWords[0])) 
		{
			if (commandWords.length > 4 || commandWords.length < 3) 
			{
				throw new CommandParseException("[ERROR]: " + incorrectNumberOfArgsMsg + " for add" + name + " command: " + details);
			}
			
			//Si no se especifica un tipo se pone un vampiro normal
			if(commandWords.length == 3)
			{
				if(Character.isDigit(commandWords[1].charAt(0)) && Character.isDigit(commandWords[2].charAt(0)))
				{
					tipo = "v";
					posicion[0] = Integer.parseInt(commandWords[1]);
					posicion[1] = Integer.parseInt(commandWords[2]);
				}
				else
				{
					throw new CommandParseException("[ERROR]: Unvalid argument for add " + name + " command, number expected: " + details);
				}
			}
			else
			{
				if(Character.isDigit(commandWords[2].charAt(0))  && Character.isDigit(commandWords[3].charAt(0)))
				{
					if(commandWords[1].equals("v") || commandWords[1].equals("d") || commandWords[1].equals("e"))
					{
						tipo = commandWords[1];
					}
					else
					{
						throw new CommandParseException("[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {' '|'D'|'E'}");
					}
					posicion[0] = Integer.parseInt(commandWords[2]);
					posicion[1] = Integer.parseInt(commandWords[3]);
				}
				else
				{
					throw new CommandParseException("[ERROR]: Unvalid argument for add" + name + " command, number expected: " + details);
				}
			}

			return this;
		}
		
		return null;
	}
} 
