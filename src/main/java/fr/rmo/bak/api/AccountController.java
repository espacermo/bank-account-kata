package fr.rmo.bak.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.rmo.bak.api.bean.AmountBean;
import fr.rmo.bak.domain.Account;
import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.service.AccountService;
import fr.rmo.bak.service.OperationService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private OperationService operationService;

	@Autowired
	private AccountService accountService;

	/**
	 * Get list of operation of a given account
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @return List of operation on the given account
	 */
	@GetMapping("/{accountNumber}/operations")
	public List<Operation> getOperationsByAccount(@PathVariable String accountNumber) {
		return operationService.getOperationsByAccount(accountNumber);
	}

	/**
	 * Deposit money in a given Account
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @param amount
	 *            amount of money
	 * @return updated account
	 */
	@PutMapping("/{accountNumber}/operations/deposit")
	public Account deposit(@PathVariable String accountNumber, @Valid @RequestBody AmountBean amount) {
		return accountService.deposit(accountNumber, amount.getValue());
	}

	/**
	 * Withdraw money in a given Account
	 * 
	 * @param accountNumber
	 *            Number of the account
	 * @param amount
	 *            amount of money
	 * @return updated account
	 */
	@PutMapping("/{accountNumber}/operations/withdraw")
	public Account withdraw(@PathVariable String accountNumber, @Valid @RequestBody AmountBean amount) {
		return accountService.withdraw(accountNumber, amount.getValue());
	}
}
