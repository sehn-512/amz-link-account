package sea.scplus.consumer.common.exception;

public class NormalRequestException extends RuntimeException 
{
	private static final long serialVersionUID = 3145741609002882987L;
	
	public NormalRequestException(String message) {
		super(message);
	}
	
}
