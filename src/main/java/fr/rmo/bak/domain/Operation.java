package fr.rmo.bak.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents the list of Operation of a given Account
 */
@Entity
public class Operation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private Account account;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false)
	private OperationType operationType;

	// no-arg constructor.
	public Operation() {
	}

	// Create Operation with current date
	public Operation(Account account, Long amount, OperationType operationType) {
		this.account = account;
		this.amount = amount;
		this.date = new Date();
		this.operationType = operationType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
}