package sea.scplus.consumer.common.exception;

public class InvalidSessionException extends RuntimeException 
{
	private static final long serialVersionUID = 3145741609002882987L;
	
	public InvalidSessionException(String message) {
		super(message);
	}
}

