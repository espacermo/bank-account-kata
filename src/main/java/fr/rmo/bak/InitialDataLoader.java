package fr.rmo.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.rmo.bak.domain.Account;
import fr.rmo.bak.repository.AccountRepository;

/**
 * Just create an Account at startup, to "play" with the application at front
 * side
 */
@Component
public class InitialDataLoader implements ApplicationRunner {

	@Autowired
	private AccountRepository accountRepository;

	public void run(ApplicationArguments args) {
		Account account = new Account();
		account.setNumber("ACC-H2G2");
		account.setBalance(0L);

		accountRepository.save(account);
	}
}
