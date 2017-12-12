package fr.rmo.bak.service;

import java.util.List;

import fr.rmo.bak.domain.Operation;

public interface OperationService {

	List<Operation> getOperationsByAccount(String accountNumber);
}
