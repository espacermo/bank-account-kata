package fr.rmo.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.rmo.bak.domain.Operation;
import fr.rmo.bak.repository.OperationRepository;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public List<Operation> getOperationsByAccount(String accountNumber) {
		return operationRepository.findOperationsByAccountNumber(accountNumber);
	}
}
