package fr.rmo.bak.common;

/**
 * Root class, represents all functionals exceptions of the application.
 * Consider as unchecked exception
 */
public abstract class FunctionalException extends RuntimeException {

	private static final long serialVersionUID = -8894351108625649026L;

	public FunctionalException(String message) {
		super(message);
	}
}
