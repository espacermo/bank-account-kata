package fr.rmo.bak;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Simple sanity check test that will fail if the application context cannot
 * start
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountApplicationTest {

	@Test
	public void contextLoads() throws Exception {
	}
}
