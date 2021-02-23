package logic.GameObjects;
import logic.Game;
import logic.GameObjectBoard;

public abstract class GameObject implements IAttack {

	protected String name;
	protected int vida;
	protected int fila;
	protected int columna;
	protected int id;
	protected boolean is_alive;
	
	protected Game game;
	
	public GameObject(Game game, int fila, int columna, int id)
	{
		this.game = game;
		this.is_alive = true;
		this.fila = fila;
		this.columna = columna;
		this.id = id;
	}
	
	protected abstract void muerte();

	
	public abstract void attack();
	public abstract void move();
	
	public String serialize()
	{
		String objectSerialize = 
				this.getName() + ";" + 
				this.getColumna() + ";" + 
				this.getFila() + ";" + 
				this.getVida() ;
		
		return objectSerialize;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getFila() 
	{
		return this.fila;
	}
	
	public int getColumna() 
	{
		return this.columna;
	}
	
	public int getVida()
	{
		return this.vida;
	}

	public boolean is_alive()
	{
		return is_alive;
	}
	
	public String toStr()
	{
		return name + " [" + this.vida + "]";
	};

	public boolean inPosition(int fila, int columna) 
	{
		if(this.fila == fila && this.columna == columna)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}








