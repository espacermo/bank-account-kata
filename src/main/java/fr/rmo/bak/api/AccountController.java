package fr.rmo.bak.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.rmo.bak.domain.Account;
import fr.rmo.bak.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PutMapping("/deposit")
	public Account deposit() {
		return accountService.deposit("CODE", 5L);
	}

	@PutMapping("/withdraw")
	public Account withdraw() {
		return accountService.withdraw("CODE", 5L);
	}

	@GetMapping("/histories")
	public List<Account> getAccountHistory() {
		return accountService.getAccountHistory();
	}
}
