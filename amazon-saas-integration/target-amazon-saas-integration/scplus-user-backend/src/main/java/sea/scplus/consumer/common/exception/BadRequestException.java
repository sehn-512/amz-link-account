package sea.scplus.consumer.common.exception;

public class BadRequestException extends RuntimeException 
{
	private static final long serialVersionUID = 3145741609002882987L;
	
	public BadRequestException(String message) {
		super(message);
	}
	
}
