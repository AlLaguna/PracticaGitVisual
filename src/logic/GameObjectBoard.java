package logic;

import logic.GameObjects.GameObject;
import logic.GameObjects.GameObjectList;
import logic.GameObjects.IAttack;
import logic.GameObjects.Vampire;

public class GameObjectBoard 
{
	private Game game;
	private GameObjectList listaObjetos;
	
	public GameObjectBoard(Game game)
	{
		this.game = game;
		this.listaObjetos = new GameObjectList();
	}
	
	public void comprobarVictoriaVampiros(int id) 
	{
		if(listaObjetos.getObjeto(id).getColumna() == -1)
		{
			game.finalJuego("Vampires win!");
		}
	}
	
	public void comprobarVictoriaSlayers()
	{
		if((game.maxVampiros - Vampire.getVampirosAparecidos()) == 0 && Vampire.getVampirosVivos() <= 0)
		{
			game.finalJuego("Player wins");
		}
	}
	
	public void añadirObjeto(int fila, int columna, GameObject objeto)
	{
		listaObjetos.añadirObjeto(objeto);
	}

	public int getNumObjetos() 
	{
		return listaObjetos.getNumObjetos();
	}

	public IAttack comprobarPosicionVacia(int fila, int columna) 
	{		
		return listaObjetos.getAttackableInPosition(fila, columna);
	}

	public IAttack getAttackableInPosition(int fila, int columna) 
	{
		return listaObjetos.getAttackableInPosition(fila, columna);
	}

	public void attack() 
	{	
		listaObjetos.attacks();
	}

	public void mover() 
	{
		listaObjetos.moves();
	}

	public String getPositionToString(int fila, int columna) 
	{
		return listaObjetos.getPositionToString(fila, columna);		
	}

	public void garlicPush() 
	{
		listaObjetos.garlicPush();
	}

	public void lightFlash() 
	{
		listaObjetos.lightFlash();
	}

	public String serializeObjects() 
	{
		return listaObjetos.serializeObjects();
	}

}