package logic;

public class Player {

	private int monedas;
	private Game game;
	public static final int monedasIniciales = 50;
	public static final int costeSlayer = 50;
	public static final int costeGP = 10;
	public static final int sumaMonedas = 10;
	public static final int SuperCoins = 1000;
	public static final int lightFlash = 50; 
	
	
	public Player (Game game) { 
	
		this.monedas = monedasIniciales; 
		this.game = game;
	}
	
	public void añadirMonedas() 
	{ 
		this.monedas = this.monedas + sumaMonedas;
	}
	
	public void añadirSuperCoins() 
	{ 
		this.monedas = this.monedas + SuperCoins;
	}
	
	public void lightFlash() 
	{ 
		if(this.monedas >= lightFlash) 
		{	
			this.monedas = this.monedas - lightFlash;
		}
		else 
		{	
			System.out.println("No tienes monedas suficientes");
		}
	}
	
	public int getMonedas() 
	{ 
		return this.monedas;
	}

	public boolean monedasSuficientes() 
	{
		if(this.monedas >= costeSlayer)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void comprarSlayer() 
	{
		this.monedas = this.monedas - costeSlayer;
	}

	public boolean puedeComprarGarlicPush() 
	{
		if(this.monedas >= costeGP)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void comprarGarlicPush() 
	{
		this.monedas = this.monedas - costeGP;
	}

	public boolean puedeComprarLightFlash() 
	{
		if(this.monedas >= lightFlash)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void comprarLightFlash() 
	{
		this.monedas = this.monedas - lightFlash;
	}

	public void sumaMonedas(int cantidad) 
	{
		this.monedas = this.monedas + cantidad;	
	}

	public boolean puedeComprarBB(int coste) 
	{
		if(this.monedas >= coste)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void comprarBB(int coste) 
	{
		this.monedas = this.monedas - coste;
	}
	
}

