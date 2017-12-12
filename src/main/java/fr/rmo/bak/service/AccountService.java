package fr.rmo.bak.service;

import fr.rmo.bak.common.AccountBalanceNegativeException;
import fr.rmo.bak.common.AccountNotFoundException;
import fr.rmo.bak.domain.Account;

public interface AccountService {

	/**
	 * Deposit money in a given Account
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @param amount
	 *            amount of money
	 * @return updated account
	 * @throws AccountNotFoundException
	 *             if account not found
	 */
	Account deposit(String accountNumber, Long amount);

	/**
	 * Withdraw money in a given Account
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @param amount
	 *            amount of money
	 * @return updated account
	 * @throws AccountNotFoundException
	 *             if account not found
	 * @throws AccountBalanceNegativeException
	 *             if balance is negative due to this operation
	 */
	Account withdraw(String accountNumber, Long amount);
}
