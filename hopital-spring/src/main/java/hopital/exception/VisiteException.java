package hopital.exception;

public class VisiteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public VisiteException() {
		
	}
	
	public VisiteException(String message) {
		super(message);
	}
}
