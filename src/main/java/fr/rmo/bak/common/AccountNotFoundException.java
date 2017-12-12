package fr.rmo.bak.common;

/**
 * Exception raised when account not found
 */
public class AccountNotFoundException extends FunctionalException {

	private static final long serialVersionUID = 5888993112981312924L;

	public AccountNotFoundException(String message) {
		super(message);
	}
}
