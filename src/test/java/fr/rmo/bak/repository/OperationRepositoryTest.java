package fr.rmo.bak.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.domain.OperationType;

/**
 * Test class for Operation repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationRepositoryTest {

	private static final String ACCOUNT_NUMBER_TEST = "ACC-007";

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private OperationRepository operationRepository;

	private Operation operation1;
	private Operation operation2;
	private Operation operation3;

	@Before
	public void setUp() {

		// Account
		Account account = new Account();
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(300L);
		testEntityManager.persist(account);

		// Operation
		operation1 = new Operation(account, 500L, OperationType.DEPOSIT);
		operation2 = new Operation(account, 100L, OperationType.DEPOSIT);
		// Simulate operation added 30sec later
		operation2.setDate(new Date(System.currentTimeMillis() + 30000));
		operation3 = new Operation(account, 200L, OperationType.WITHDRAW);
		// Simulate operation added 1mn later
		operation3.setDate(new Date(System.currentTimeMillis() + 60000));

		testEntityManager.persist(operation1);
		testEntityManager.persist(operation2);
		testEntityManager.persist(operation3);
	}

	@Test
	public void whenFindOperationsByAccountThenOk() {
		List<Operation> operations = operationRepository.findOperationsByAccountNumber(ACCOUNT_NUMBER_TEST);

		assertThat(operations).isNotNull();
		assertThat(operations.size()).isEqualTo(3);
		// Check also order
		assertThat(operations).containsExactly(operation3, operation2, operation1);
	}
}