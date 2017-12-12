package fr.rmo.bak.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.domain.OperationType;
import fr.rmo.bak.repository.OperationRepository;

/**
 * Test class for Operation Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceImplTest {

	private static final String ACCOUNT_NUMBER_TEST = "ACC-007";

	@Autowired
	private OperationService operationService;

	@MockBean
	private OperationRepository operationRepository;

	@Test
	public void whenOperationsByAccountThenOk() {

		Account account = new Account();
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(300L);

		// Operation
		Operation operation1 = new Operation(account, 500L, OperationType.DEPOSIT);
		Operation operation2 = new Operation(account, 100L, OperationType.WITHDRAW);
		Operation operation3 = new Operation(account, 100L, OperationType.WITHDRAW);

		Mockito.when(operationRepository.findOperationsByAccountNumber(ACCOUNT_NUMBER_TEST))
				.thenReturn(Arrays.asList(operation1, operation2, operation3));

		List<Operation> operations = operationService.getOperationsByAccount(ACCOUNT_NUMBER_TEST);

		assertThat(operations).isNotNull();
		assertThat(operations.size()).isEqualTo(3);
		assertThat(operations).containsExactly(operation1, operation2, operation3);
	}
}