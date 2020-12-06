package taxcalc;

/**
 * 
 * @author
 * class to throw the exception in the application.
 *
 */

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	ApplicationException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}