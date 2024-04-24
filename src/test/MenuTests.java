package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	@Test
	void testUserDeposit() throws IOException {
		Menu m = new Menu();
		m.processingUserSelection(1);
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
	}


	@Test

	void testTerminateAccount() throws IOException {

		Menu m = new Menu();

		m.createAccount("John");

		m.terminateAccount("John");

		assertFalse(m.readData("John"));

	}

	@Test

	void testTerminateNonExistentAccount() throws IOException {

		Menu m = new Menu();

		m.terminateAccount("John");

		assertFalse(m.readData("John"));

	}
	
	@Test
	
	void testChangeUsername() throws IOException {
		Menu m = new Menu();
		m.createAccount("John");
		m.changeUsername("John2");
		assertTrue(m.readData("John2"));
		assertFalse(m.readData("John"));
	}

}
