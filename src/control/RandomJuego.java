package control;
import java.util.Random;

public class RandomJuego { 

	private Random rand;
	
	public RandomJuego(long seed) { 
		
		rand = new Random(seed);
		
	}

	public int numrand(int enteroMax) { 

		return rand.nextInt(enteroMax + 1);
		
	}

	public double frecuenciaAleatoria() { 
		return rand.nextDouble();
		
	}
	
}




