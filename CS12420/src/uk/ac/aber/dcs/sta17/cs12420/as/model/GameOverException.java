package uk.ac.aber.dcs.sta17.cs12420.as.model;

/**
 * This exception is created with the intension of appearing if game play is
 * attempted when it is actually over or similar circumstances.
 * 
 * @author Steven
 * @version 25.04.2014
 * 
 */
@SuppressWarnings("serial")
public class GameOverException extends RuntimeException {

	public GameOverException() {
	}

	public GameOverException(String message) {
		super(message);
	}

	public GameOverException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameOverException(Throwable cause) {
		super(cause);
	}
}