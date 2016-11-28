package es.throughworks.test3;

/**
 * Self Made Exception. throw when sintax is not correct
 * @author Usuario
 */

public class UnderstandableException extends Exception {
	  public UnderstandableException() { super(); }
	  public UnderstandableException(String message) { super(message); }
	  public UnderstandableException(String message, Throwable cause) { super(message, cause); }
	  public UnderstandableException(Throwable cause) { super(cause); }
}