package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject
{
	private String nombre = "S";
	private static final int vidaInicial = 3;
	public static final String victoriaMsg = "Jugador";
	private static final int HARM = 1;
	
	public Slayer(Game game, int fila, int columna, int id) 
	{
		super(game, fila, columna, id);
		this.name = nombre;
		vida = vidaInicial;
	}
	
	public void attack()
	{
		boolean alcanzado = false;
		if (is_alive()) 
		{
			IAttack other = null;
			int i = columna + 1;
			while(i < game.getColumnas() && !alcanzado) 
			{
				other = game.getAttackableInPosition(fila, i);
				if (other != null)
				{
					alcanzado = other.receiveSlayerAttack(HARM);
				}
				
				i++;
			}
		}
	}
	
	public void move()
	{
		//
	}
	
	public String ganador()
	{
		return victoriaMsg;
	}


	protected void muerte() 
	{
		this.is_alive = false;
	}

	public boolean receiveVampireAttack(int daño)
	{
		this.vida = this.vida - daño;
		if(this.vida <= 0)
		{
			this.muerte();
			return true;
		}
		return false;
	}
	
	public boolean receiveDraculaAttack()
	{
		this.vida = this.vida - this.vida;
		this.muerte();
		return true;
	}

	public void modificarDaño(int modificacion) 
	{
		//	
	}



}
