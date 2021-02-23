package logic.GameObjects;

import logic.Game;

public class Vampire extends GameObject
{
	private String nombre = "V";
	private static final int vidaInicial = 5;
	
	private static int vampirosAparecidos = 0;
	protected static int vampirosVivos = 0;
	
	private int harm = 1;
	protected int ciclos;
	
	public Vampire(Game game, int fila, int columna, int id) 
	{	
		super(game, fila, columna, id);
		this.name = nombre;
		vida = vidaInicial;
		this.ciclos = 1;
		
		vampirosAparecidos++;
		vampirosVivos++;
	}
	
	public static int getVampirosAparecidos()
	{
		return vampirosAparecidos;
	}
	
	public static int getVampirosVivos()
	{
		return vampirosVivos;
	}
	
	public void modificarDaño(int modificacion)
	{
		this.harm = harm + modificacion;
	}
	
	public void attack()
	{
		if (is_alive()) 
		{
			IAttack other = game.getAttackableInPosition(fila, columna - 1);
			if (other != null ) 
			{
				other.receiveVampireAttack(harm);
			}
		}
	}
	
	public void move()
	{	
		if(ciclos <= 0)
		{
			if(game.getAttackableInPosition(this.fila, this.columna - 1) == null)
			{
				ciclos = 2;
				this.columna--;
				game.comprobarVictoriaVampiros(id);
			}
		}
				
		this.ciclos--;
	}

	protected void muerte()
	{
		this.is_alive = false;
		
		vampirosVivos--;
	}
	
	public boolean receiveSlayerAttack(int daño)
	{
		this.vida = this.vida - daño;
		if(this.vida <= 0)
		{
			this.muerte();
		}
		return true;
	}

	public boolean receiveGarlicPush() 
	{
		if(columna == game.getColumnas() - 1)
		{
			muerte();
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
		muerte();
		return true;
	}

	public String serialize() 
	{
		return super.serialize() + ";" + this.ciclos;
		
	}
	
}