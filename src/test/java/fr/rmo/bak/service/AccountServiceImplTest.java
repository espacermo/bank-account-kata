package fr.rmo.bak.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.common.AccountBalanceNegativeException;
import fr.rmo.bak.common.AccountNotFoundException;
import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.domain.OperationType;
import fr.rmo.bak.repository.AccountRepository;
import fr.rmo.bak.repository.OperationRepository;

/**
 * Test class for Account Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

	private static final String ACCOUNT_NUMBER_TEST_OK = "ACC-007";
	private static final String ACCOUNT_NUMBER_TEST_KO = "DUMMY";

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private OperationRepository operationRepository;

	@Before
	public void setUp() {
		Account initialAccount = new Account();
		initialAccount.setId(1L);
		initialAccount.setNumber(ACCOUNT_NUMBER_TEST_OK);
		initialAccount.setBalance(1000L);

		Operation operation = new Operation(initialAccount, 1000L, OperationType.DEPOSIT);

		Mockito.when(accountRepository.findByNumber(ACCOUNT_NUMBER_TEST_OK)).thenReturn(Optional.of(initialAccount));
		Mockito.when(accountRepository.findByNumber(ACCOUNT_NUMBER_TEST_KO)).thenReturn(Optional.empty());
		Mockito.when(operationRepository.save(operation)).thenReturn(operation);
	}

	@Test
	public void whenDepositThenAmountUpdated() {
		Account finalAccount = new Account();
		finalAccount.setId(1L);
		finalAccount.setNumber(ACCOUNT_NUMBER_TEST_OK);
		finalAccount.setBalance(1100L);

		Mockito.when(accountRepository.save(any(Account.class))).thenReturn(finalAccount);

		Account account = accountService.deposit(ACCOUNT_NUMBER_TEST_OK, 100L);

		assertThat(account).isNotNull();
		assertThat(account).isEqualToComparingFieldByField(finalAccount);
	}

	@Test
	public void whenWithdrawThenAmountUpdated() {
		Account finalAccount = new Account();
		finalAccount.setId(1L);
		finalAccount.setNumber(ACCOUNT_NUMBER_TEST_OK);
		finalAccount.setBalance(900L);

		Mockito.when(accountRepository.save(any(Account.class))).thenReturn(finalAccount);

		Account account = accountService.withdraw(ACCOUNT_NUMBER_TEST_OK, 100L);

		assertThat(account).isNotNull();
		assertThat(account).isEqualToComparingFieldByField(finalAccount);
	}

	@Test(expected = AccountBalanceNegativeException.class)
	public void whenWithdrawThenAccountBalanceNegative() {
		accountService.withdraw(ACCOUNT_NUMBER_TEST_OK, 2000L);
	}

	@Test(expected = AccountNotFoundException.class)
	public void whenWithdrawThenAccountNotFound() {
		accountService.withdraw(ACCOUNT_NUMBER_TEST_KO, 2000L);
	}
}