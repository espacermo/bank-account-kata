package fr.rmo.bak.service;

import java.util.List;

import fr.rmo.bak.domain.Account;

public interface AccountService {

	Account deposit(String accountNumber, Long amount);

	Account withdraw(String accountNumber, Long amount);

	List<Account> getAccountHistory();
}
