package fr.rmo.bak.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.repository.AccountRepository;

/**
 * Test class for Account Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;

	@Test
	public void whenDepositThenAmountUpdated() {

	}

	@Test
	public void whenWithdrawThenAmountUpdated() {

	}
}