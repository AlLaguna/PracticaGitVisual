package logic.GameObjects;

import logic.Game;

public class Dracula extends Vampire
{

	private String nombre = "D";
	private int ciclos;
	private static boolean draculaAlive = false;
	
	public Dracula(Game game, int fila, int columna, int id) 
	{
		super(game, fila, columna, id);
		this.name = nombre;
		
		draculaAlive = true;
	}
	
	public static boolean isDraculaAlive()
	{
		return draculaAlive;
	}
	
	public void attack()
	{
		if (is_alive()) 
		{
			IAttack other = game.getAttackableInPosition(fila, columna - 1);
			if (other != null ) 
			{
				other.receiveDraculaAttack();
			}
		}
	}
	
	protected void muerte()
	{
		this.is_alive = false;
		vampirosVivos--;
		
		draculaAlive = false;
	}
	
	
	public boolean receiveLightFlash() 
	{
		return true;
	}

}
