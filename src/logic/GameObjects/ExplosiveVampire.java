package logic.GameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire
{
	private String nombre = "EV";

	private static final int daño = 1;
	
	public ExplosiveVampire(Game game, int fila, int columna, int id) 
	{
		super(game, fila, columna, id);
		this.name = nombre;
	}
	
	protected void muerte()
	{
		this.is_alive = false;

		vampirosVivos--;
		this.explosion();
	}
	
	private void explosion()
	{
		IAttack objeto;
	
		for(int i = -1; i <= 1; i++)
		{
			for(int j = 1; j >= -1; j-- )
			{
				objeto = game.getAttackableInPosition(fila + i, columna + j);
				
				if(objeto != null)
				{
					objeto.receiveSlayerAttack(daño);
				}
			}
		}
			
	}
	
	public boolean receiveGarlicPush() 
	{
		if(columna == game.getColumnas() - 1)
		{
			this.is_alive = false;
			vampirosVivos--;
			
		}
		else
		{
			this.columna = this.columna + 1;
			this.ciclos = 1;
		}
		return true;
	}
	
	public boolean receiveLightFlash() 
	{
		this.is_alive = false;
		vampirosVivos--;
		
		return true;
	}
}
