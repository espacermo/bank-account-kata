package fr.rmo.bak.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.rmo.bak.domain.Account;
import fr.rmo.bak.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	@Override
	public Account test() {
		// Return nothing, just to test
		return accountRepository.findOne(1L);
	}

}
