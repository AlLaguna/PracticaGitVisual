package control;

public class GameException extends Exception 
{
	private String error;
	
	public GameException(String error)
	{
		this.error = error;
		//System.out.print(this.error);
	}
	
	
	public String getMessage()
	{
		return error;
	}
}