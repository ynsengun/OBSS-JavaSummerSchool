package exceptions;

public class MyUncheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3361381254775983776L;
	
	public MyUncheckedException( Throwable ex) {
		super(ex);
		
	}

}
