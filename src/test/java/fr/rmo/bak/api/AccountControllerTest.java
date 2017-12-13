package fr.rmo.bak.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.rmo.bak.common.AccountBalanceNegativeException;
import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.domain.OperationType;
import fr.rmo.bak.service.AccountService;
import fr.rmo.bak.service.OperationService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	private static final String ACCOUNT_NUMBER_TEST = "ACC-007";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@MockBean
	private OperationService operationService;

	@Test
	public void whenGetOperationOfAccountThenOk() throws Exception {

		// Account
		Account account = new Account();
		account.setId(1L);
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(300L);

		// Operation
		Operation operation1 = new Operation(account, 500L, OperationType.DEPOSIT);
		operation1.setId(1L);
		operation1.setDate(new Date(10L));
		Operation operation2 = new Operation(account, 100L, OperationType.DEPOSIT);
		operation2.setId(2L);
		operation2.setDate(new Date(20L));
		Operation operation3 = new Operation(account, 200L, OperationType.WITHDRAW);
		operation3.setId(3L);
		operation3.setDate(new Date(30L));

		Mockito.when(operationService.getOperationsByAccount(ACCOUNT_NUMBER_TEST))
				.thenReturn(Arrays.asList(operation1, operation2, operation3));

		// Perform Test
		mockMvc.perform(get("/api/accounts/{accountNumber}/operations", ACCOUNT_NUMBER_TEST)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(content().json(
						"[{\"id\":1,\"account\":{\"id\":1,\"number\":\"ACC-007\",\"balance\":300},\"date\":10,\"amount\":500,\"operationType\":\"DEPOSIT\"},{\"id\":2,\"account\":{\"id\":1,\"number\":\"ACC-007\",\"balance\":300},\"date\":20,\"amount\":100,\"operationType\":\"DEPOSIT\"},{\"id\":3,\"account\":{\"id\":1,\"number\":\"ACC-007\",\"balance\":300},\"date\":30,\"amount\":200,\"operationType\":\"WITHDRAW\"}]"));
	}

	@Test
	public void whenDepositAmountThenOk() throws Exception {
		// Account
		Account account = new Account();
		account.setId(1L);
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(300L);

		Mockito.when(accountService.deposit(ACCOUNT_NUMBER_TEST, 200L)).thenReturn(account);

		// Perform Test
		mockMvc.perform(put("/api/accounts/{accountNumber}/operations/deposit", ACCOUNT_NUMBER_TEST)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"value\": 200}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(1))).andExpect(jsonPath("$.balance", equalTo(300)));
	}

	@Test
	public void whenWithdrawAmountThenOk() throws Exception {
		// Account
		Account account = new Account();
		account.setId(1L);
		account.setNumber(ACCOUNT_NUMBER_TEST);
		account.setBalance(100L);

		Mockito.when(accountService.withdraw(ACCOUNT_NUMBER_TEST, 200L)).thenReturn(account);

		// Perform Test
		mockMvc.perform(put("/api/accounts/{accountNumber}/operations/withdraw", ACCOUNT_NUMBER_TEST)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"value\": 200}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(1))).andExpect(jsonPath("$.balance", equalTo(100)));
	}

	@Test
	public void whenWithdrawAmountThenNegativeBalance() throws Exception {
		Mockito.when(accountService.withdraw(ACCOUNT_NUMBER_TEST, 5000L))
				.thenThrow(new AccountBalanceNegativeException("Exception message"));

		// Perform Test
		mockMvc.perform(put("/api/accounts/{accountNumber}/operations/withdraw", ACCOUNT_NUMBER_TEST)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"value\": 5000}"))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void whenDepositWithNegativeAmountThenNegativeKo() throws Exception {
		// Perform Test
		mockMvc.perform(put("/api/accounts/{accountNumber}/operations/deposit", ACCOUNT_NUMBER_TEST)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"value\": -22}"))
				.andExpect(status().isBadRequest());
	}
}
