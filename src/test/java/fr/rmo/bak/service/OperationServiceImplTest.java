package fr.rmo.bak.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.rmo.bak.repository.OperationRepository;

/**
 * Test class for Operation Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceImplTest {

	@Autowired
	private OperationService operationService;

	@MockBean
	private OperationRepository operationRepository;

	@Test
	public void whenOperationsByAccountThenOk() {

	}
}