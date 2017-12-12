package fr.rmo.bak.service;

import java.util.List;

import fr.rmo.bak.domain.Operation;

public interface OperationService {

	/**
	 * Get the list of operation for an Account. Consider no issue if account
	 * doesn't exist
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @return List of operation done on the given account
	 */
	List<Operation> getOperationsByAccount(String accountNumber);
}
