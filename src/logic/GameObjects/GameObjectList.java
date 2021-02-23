package logic.GameObjects;

import java.util.ArrayList;

public class GameObjectList 
{
	
	private ArrayList<GameObject> gameObjects;
	private int numObjetos;

	
	public GameObjectList()
	{
		this.gameObjects = new ArrayList<GameObject>();
		this.numObjetos = 0;
		
	}
	
	public void añadirObjeto(GameObject objeto)
	{
		gameObjects.add(objeto);
		
		this.numObjetos++;
	}

	public int getNumObjetos() 
	{
		return numObjetos;
	}
	
	public GameObject getObjeto(int id)
	{
		GameObject objeto = gameObjects.get(id);
		return objeto;
	}

	public IAttack getAttackableInPosition(int fila, int columna) 
	{
		GameObject objeto = null;

		boolean encontrado = false;
		int i = 0;
		
		while(i < this.numObjetos && !encontrado)
		{
			objeto = gameObjects.get(i);

			if(objeto.is_alive())
			{
				if(objeto.inPosition(fila, columna))
				{
					return objeto;
				}
			}
			i++;
		}
		
		return null;
	}

	public void attacks() 
	{
		for(GameObject objeto: gameObjects)
		{
			if(objeto.is_alive())
			{
				objeto.attack();
			}
		}
	}

	public void moves() 
	{	
		for(GameObject objeto: gameObjects)
		{
			if(objeto.is_alive())
			{
				objeto.move();
			}
		}
	}

	public String getPositionToString(int fila, int columna) 
	{
		GameObject objeto = null;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < this.numObjetos)
		{			
			objeto = gameObjects.get(i);
			if(objeto.is_alive()) 
			{
				if(objeto.getFila() == fila && objeto.getColumna() == columna)
				{
					encontrado = true;
					return objeto.toStr();
				}
			}
			i++;
		}
		return " ";	
	}

	public void reset() 
	{
		this.gameObjects = new ArrayList<GameObject>();
		this.numObjetos = 0;
		//this.numVampiros = 0;
	}

	public void garlicPush() 
	{
		for(GameObject objeto: gameObjects)
		{
			if(objeto.is_alive())
			{	
				if(getAttackableInPosition(objeto.getFila(), objeto.getColumna() + 1) == null)
				{
					objeto.receiveGarlicPush();
				}				
			}
		}	
		
	}

	public void lightFlash()  
	{
		for(GameObject objeto: gameObjects)
		{
			if(objeto.is_alive())
			{	
				objeto.receiveLightFlash();	
			}
		}
	}

	/*public boolean is_draculaVivo() 
	{
		return is_draculaAlive;
	}*/

	public String serializeObjects() 
	{
		String serializedObjects = "";
		
		for(GameObject objeto: gameObjects)
		{
			if(objeto.is_alive())
			{	
				serializedObjects = serializedObjects + 
				 objeto.serialize() + "\n"; 
			}
		}
		
		return serializedObjects;
		
	}

}
