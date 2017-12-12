package fr.rmo.bak.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.rmo.bak.common.AccountBalanceNegativeException;
import fr.rmo.bak.common.AccountNotFoundException;
import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.domain.OperationType;
import fr.rmo.bak.repository.AccountRepository;
import fr.rmo.bak.repository.OperationRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Transactional
	@Override
	public Account deposit(String accountNumber, Long amount) {
		Account account = accountRepository.findByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(String.format("Account %s not found", accountNumber)));

		Operation operation = new Operation(account, amount, OperationType.DEPOSIT);
		operationRepository.save(operation);

		account.setBalance(account.getBalance() + amount);
		return accountRepository.save(account);
	}

	@Transactional
	@Override
	public Account withdraw(String accountNumber, Long amount) {
		Account account = accountRepository.findByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(String.format("Account %s not found", accountNumber)));

		if (account.getBalance() - amount < 0) {
			throw new AccountBalanceNegativeException(
					String.format("Amount %s too high, negative balance for the account %s", amount, accountNumber));
		}

		Operation operation = new Operation(account, amount, OperationType.WITHDRAW);
		operationRepository.save(operation);

		account.setBalance(account.getBalance() - amount);
		return accountRepository.save(account);
	}
}
