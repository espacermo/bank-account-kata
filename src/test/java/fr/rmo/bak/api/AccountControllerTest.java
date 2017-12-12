package fr.rmo.bak.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
	public void whenGetOperationOfAccountThenOk() {

	}

	@Test
	public void whenDepositAmountThenOk() {

	}

	@Test
	public void whenWithdrawAmountThenOk() {

	}
}
