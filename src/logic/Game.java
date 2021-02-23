package logic;

import java.util.Random;

import control.CommandExecuteException;
import control.DraculaIsAliveException;
import control.NotEnoughCoinsException;
import control.UnvalidPositionException;
import control.NoMoreVampiresException;
import logic.GameObjects.BloodBank;
import logic.GameObjects.Dracula;
import logic.GameObjects.ExplosiveVampire;
import logic.GameObjects.IAttack;
import logic.GameObjects.Slayer;
import logic.GameObjects.Vampire;
import logic.GameObjects.Dracula;
import view.GamePrinter;
import view.IPrintable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class Game implements IPrintable
{
	private int filas;
	private int columnas;
	
	public static final int maxArrays = 99;
	public static final int ciclosIniciales = 0;

	private static final String addBB = String.format("add bank");
	private static final String addSlayer = String.format("add slayer");
	private static final String addVampire = String.format("add this vampire");
	
	private static final String errorPrompt = String.format("[ERROR]: ");
	private static final String failedPrompt = String.format(errorPrompt + "Failed to ");

	private static final String Position = String.format(errorPrompt + "Position (");
	private static final String UnvalidPositionException = String.format("): Unvalid position\n" + failedPrompt);
	private static final String DraculaIsAliveException = String.format(errorPrompt + " Dracula is already on board\n" + failedPrompt + "add this vampire");
	private static final String NoMoreVampiresException = String.format(errorPrompt  + "No more remaining vampires left\n" + failedPrompt + "add this vampire");
	private static final String NotEnoughCoinsException = String.format(" : Not enough coins\n" + failedPrompt);
	private static final String DefenderCost = String.format(errorPrompt + "Defender cost is ");
	private static final String FlashCost = String.format(errorPrompt + "Light Flash cost is ");
	private static final String GarlicCost = String.format(errorPrompt + "GarlicPush cost is ");
	
	private GamePrinter printer;
	private GameObjectBoard board;
	private Player player;
	private Random rand;
	private double frecuenciaVampiros;
	private boolean is_PartidaAcabada;
	private int ciclos;
	
	
	public int maxVampiros;
	
	
	private boolean varFinal = false;
	private Level level;
	private String ganador;
	
	
	public Game(long seed, Level level)
	{
		this.level = level;
		filas = level.getX();
		columnas = level.getY();
		printer = new GamePrinter(this, filas, columnas);
		frecuenciaVampiros = level.getFrecuencia();
		rand = new Random(seed);
		player = new Player(this);

		reset();	
	}
	
	public void reset()
	{	
		this.ciclos = ciclosIniciales;
		board = new GameObjectBoard(this);
		player = new Player(this);
		is_PartidaAcabada = false;
		maxVampiros = level.getMaxVampires();	
	}

	public void update()
	{
		this.ciclos++;
		this.añadirMonedas();
		
		this.mover();
		this.atack();
		this.añadirVampiros();
		
		this.comprobarVictoriaSlayers();	
	}
	
	private void atack() 
	{
		board.attack();	
	}

	public int getFilas()
	{
		return this.filas;
	}
	
	public int getColumnas()
	{
		return this.columnas;
	}
	
	private void comprobarVictoriaSlayers() 
	{
		board.comprobarVictoriaSlayers();		
	}

	private void mover() 
	{
		board.mover();
	}
	
	private void añadirMonedas() 
	{
		if(rand.nextFloat() > 0.5) 
		{
			player.añadirMonedas();
		}
	}
	
	public void sumaMonedas(int cantidad)
	{
		player.sumaMonedas(cantidad);
	}
	
	public void añadirSuperCoins() 
	{ 
		player.añadirSuperCoins();
	}

	public void añadirVampiros()
	{
		int fila;

		if((this.maxVampiros - Vampire.getVampirosAparecidos()) > 0 && rand.nextDouble() < frecuenciaVampiros)
		{
			fila = rand.nextInt(filas - 1 + 1);
			
			if(board.getAttackableInPosition(fila, this.columnas - 1) == null)
			{
				board.añadirObjeto(fila, this.columnas - 1, this.crearVampiro(fila, this.columnas - 1));
				//this.restarVampirosRestantes();
			}
		}
		
		if(!Dracula.isDraculaAlive())
		{
			if((this.maxVampiros - Vampire.getVampirosAparecidos()) > 0 && rand.nextDouble() < frecuenciaVampiros)
			{
				fila = rand.nextInt(filas - 1 + 1);
				
				if(board.getAttackableInPosition(fila, this.columnas - 1) == null)
				{
					board.añadirObjeto(fila, this.columnas - 1, this.crearDracula(fila, this.columnas - 1));
				}
			}
		}	
		
		if((this.maxVampiros - Vampire.getVampirosAparecidos()) > 0 && rand.nextDouble() < frecuenciaVampiros)
		{
			fila = rand.nextInt(filas - 1 + 1);
			
			if(board.getAttackableInPosition(fila, this.columnas - 1) == null)
			{
				board.añadirObjeto(fila, this.columnas - 1, this.crearExplosiveVampire(fila, this.columnas - 1));
			}
		}
	}
	
	public boolean is_Partida_Acabada ()
	{
		return is_PartidaAcabada;
	}

	
	public String toString()
	{
		return printer.toString();
	}
	
	public boolean añadirSlayer(int fila, int columna) throws CommandExecuteException
	{
		if(this.posicionValida(fila, columna, this.getColumnas() - 1)) 
		{
			if(board.getAttackableInPosition(fila, columna) == null)
			{
				if(player.monedasSuficientes())
				{
					player.comprarSlayer();
					board.añadirObjeto(fila, columna, this.crearSlayer(fila, columna));

					return true;
				}
				else 
				{
					throw new NotEnoughCoinsException(DefenderCost + player.costeSlayer + NotEnoughCoinsException + addSlayer);
				}
			}
			else 
			{
				throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addSlayer);
			}
		}
		else 
		{
			throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addSlayer);
		}					
	}
	
	private boolean posicionValida(int fila, int columna, int maxColumna) throws CommandExecuteException
	{
		if(fila >= 0 && fila < this.getFilas() && columna >= 0 && columna < maxColumna)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public boolean añadirBB(int fila, int columna, int coste)  throws CommandExecuteException
	{
		if(this.posicionValida(fila, columna, this.getColumnas() - 1))
		{
			if(board.getAttackableInPosition(fila, columna) == null)
			{
				if(player.puedeComprarBB(coste)) 
				{
					player.comprarBB(coste);
					board.añadirObjeto(fila, columna, this.crearBB(fila, columna, coste));
			
					return true;
				}
				else
				{
					throw new NotEnoughCoinsException(DefenderCost + player.costeSlayer + NotEnoughCoinsException + addBB);
				}
			}
			else 
			{
				throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addBB);
			}
		}
		else
		{
			throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addBB);
		}
			
		
	}
	
	public void añadirVampiroManual(String tipo, int fila, int columna) throws CommandExecuteException
	{
		if ((this.maxVampiros - Vampire.getVampirosAparecidos()) > 0) 
		{
			if (this.posicionValida(fila, columna, this.getColumnas())) 
			{
				if (board.getAttackableInPosition(fila, columna) == null) 
				{
					switch (tipo) 
					{
					case "v":
						board.añadirObjeto(fila, columna, this.crearVampiro(fila, columna));
						break;
						
						case "d":
						if (Dracula.isDraculaAlive()) 
						{
							throw new DraculaIsAliveException(DraculaIsAliveException);

						}
						else 
						{
							board.añadirObjeto(fila, columna, this.crearDracula(fila, columna));
						}
						break;
						
						case "e":
						board.añadirObjeto(fila, columna, this.crearExplosiveVampire(fila, columna));
						break;
					}
					
				} 
				else 
				{
					throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addVampire);
				}
			} 
			else 
			{
				throw new UnvalidPositionException(Position + columna + ", " + fila + UnvalidPositionException + addVampire);
			}
		} 
		else 
		{
			throw new NoMoreVampiresException(NoMoreVampiresException);
		} 
	}
	
	public void comprobarVictoriaVampiros(int id) 
	{
		board.comprobarVictoriaVampiros(id);
	}
	
	public void finalJuego(String ganador)
	{
		if(!varFinal)
			this.ciclos--;
		
		varFinal = true;
		is_PartidaAcabada = true;
		this.ganador = ganador;
		
	}

	public int getCiclos() 
	{
		return this.ciclos;
	}

	public void exit() 
	{
		System.out.print("[GAME OVER] Nobody wins...");
		System.exit(0);
	}

	/*public int getVampirosRestantes() 
	{
		return this.vampirosRestantes;
	}*/
	
	public int getMonedas()
	{
		return player.getMonedas();
	}
	
	public void lightFlash() throws CommandExecuteException
	{ 
		
		if(player.puedeComprarLightFlash())
		{
			player.comprarLightFlash();
			board.lightFlash();
		}
		else
		{
			throw new NotEnoughCoinsException(FlashCost + player.lightFlash + NotEnoughCoinsException + "Light Flash");
		}
	}

	public Vampire crearVampiro(int fila, int columna) 
	{
		return new Vampire(this, fila, columna, board.getNumObjetos());
	}
	
	public Dracula crearDracula(int fila, int columna) // NUEVO
	{
		return new Dracula(this, fila, columna, board.getNumObjetos());
	}
	
	public ExplosiveVampire crearExplosiveVampire(int fila, int columna) // NUEVO
	{
		return new ExplosiveVampire(this, fila, columna, board.getNumObjetos());
	}
	
	public Slayer crearSlayer(int fila, int columna) 
	{
		return new Slayer(this, fila, columna, board.getNumObjetos());
	}
	
	public BloodBank crearBB(int fila, int columna, int coste) 
	{
		return new BloodBank(this, fila, columna, board.getNumObjetos(), coste);
	}

	public boolean isFinished() 
	{
		return this.is_PartidaAcabada;
	}

	public String getWinnerMessage() 
	{
		return ganador;
	}

	public IAttack getAttackableInPosition(int fila, int columna) 
	{
		return board.getAttackableInPosition(fila, columna);	
	}

	public String getPositionToString(int fila, int columna) 
	{
		return board.getPositionToString(fila, columna);
	}

	public String getInfo() 
	{
		String info = "Number of cycles: " + this.getCiclos() + 
				"\n" + "Coins: " + this.getMonedas() + 
				"\n" + "Remaining vampires: " + (this.maxVampiros - Vampire.getVampirosAparecidos()) + 
				"\n" + "Vampires on the board: " + Vampire.getVampirosVivos() + 
				"\n";
		if(Dracula.isDraculaAlive())
		{
			info = info + "Dracula is alive \n";
		}
		return info;
	}

	public void garlicPush() throws CommandExecuteException 
	{
		if(player.puedeComprarGarlicPush())
		{
			player.comprarGarlicPush();
			board.garlicPush();
		}
		else
		{
			throw new NotEnoughCoinsException(GarlicCost + player.costeGP + NotEnoughCoinsException + "Garlic Push");
		}
		
	}

	public String serialize() 
	{
		String juegoSerializado = null;
		juegoSerializado = "Cycles: " + this.ciclos + "\n" +
		 "Coins: " + player.getMonedas() + "\n" +	
		 "Level: " + level.getName().toUpperCase() + "\n" +
		 "Remaining Vampires: " + (this.maxVampiros - Vampire.getVampirosAparecidos()) + "\n" + 
		 "Vampires on Board: " + Vampire.getVampirosVivos() + "\n\n" +
		 
		 "Game Object List: \n" +
		 
		 board.serializeObjects() + "\n\n";
		
		
		return juegoSerializado;
	}


	public void saveGame(String fileName) 
	{
		String juegoSerializado = this.serialize();
		
		try 
		{
			String ruta = fileName;

			File archivo = new File(ruta);
        
        	if (!archivo.exists()) 
        	{
        		archivo.createNewFile();
        	}
        	FileWriter fw = new FileWriter(archivo);
        	BufferedWriter bw = new BufferedWriter(fw);
        	
        	
        	
        	bw.write("Buffy the Vampire Slayer v3.0\n\n" + juegoSerializado);
        	bw.close();
        	
        	System.out.print("Game successfully saved to file " + fileName + ".\n");
		} 
		catch (Exception e) 
		{
			System.out.print("[ERROR]: No se ha podido guardar el archivo");
			e.printStackTrace();
	    }
	}

}



