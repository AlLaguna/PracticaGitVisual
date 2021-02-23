package logic.GameObjects;

import logic.Game;

public class BloodBank extends Slayer
{
	private String nombre = "B";
	private static int vidaInicial = 1;
	private int coste;
	
	public BloodBank(Game game, int fila, int columna, int id, int coste) 
	{
		super(game, fila, columna, id);
		vida = vidaInicial;
		this.name = nombre;
		this.coste = coste;
	}

	public void move()
	{
		game.sumaMonedas(this.coste*10/100);
	}
	
	public String toStr() 
	{
		return "B [" + this.coste + "]";
	}

	public void attack()
	{
		//
	}
	
	public String serialize() 
	{
		return super.serialize() + ";" + this.coste;
	}
	
}
