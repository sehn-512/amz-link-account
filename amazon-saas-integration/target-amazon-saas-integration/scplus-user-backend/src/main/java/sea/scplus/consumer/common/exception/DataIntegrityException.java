package sea.scplus.consumer.common.exception;

public class DataIntegrityException extends RuntimeException 
{
	private static final long serialVersionUID = 3145741609002882987L;
	
	public DataIntegrityException(String message) {
		super(message);
	}
	
}
