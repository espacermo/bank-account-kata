package fr.rmo.bak.common;

/**
 * Exception raised when an operation implies a negative balance for a given
 * account
 */
public class AccountBalanceNegativeException extends FunctionalException {

	private static final long serialVersionUID = -1185393940678305322L;

	public AccountBalanceNegativeException(String message) {
		super(message);
	}
}
