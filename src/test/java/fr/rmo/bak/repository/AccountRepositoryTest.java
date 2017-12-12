package fr.rmo.bak.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.domain.Account;

/**
 * Test class for Account repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	private static final String ACCOUNT_NUMBER_TEST = "ACC-007";

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AccountRepository accountRepository;

	@Before
	public void setUp() {
		Account account = new Account();
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(0L);

		testEntityManager.persist(account);
	}

	@Test
	public void whenFindByAccountNumberThenOk() {
		Optional<Account> account = accountRepository.findByNumber(ACCOUNT_NUMBER_TEST);

		assertThat(account).isNotNull();
		assertThat(account.isPresent()).isTrue();
		assertThat(account.get().getNumber()).isEqualTo(ACCOUNT_NUMBER_TEST);
	}

	@Test
	public void whenFindByAccountNumberThenNotFound() {
		Optional<Account> account = accountRepository.findByNumber("DUMMY");

		assertThat(account).isNotNull();
		assertThat(account.isPresent()).isFalse();
	}
}