package uk.ac.aber.dcs.sta17.cs12420.as.model;

/**
 * This exception is created with the intension of appearing if game play is
 * attempted when the list is 0, thus contains 0 words or phrases.
 * 
 * @author Steven
 * @version 25.04.2014
 * 
 */
@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {

	public EmptyListException() {
	}

	public EmptyListException(String message) {
		super(message);
	}

	public EmptyListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyListException(Throwable cause) {
		super(cause);
	}
}