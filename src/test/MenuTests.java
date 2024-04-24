package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	// need  more tests
	
	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		m.createAccount("Nicole");
		//user has provided value input of 50
		m.processingUserSelection(1);
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
	}
	
//	@Test
//	void testMultipleUserDeposit() {
//		Menu m = new Menu();
//		//user has provided value input of 50
//		m.createAccount("John");
//		m.processingUserSelection(50);
//		m.processingUserSelection(100);
//		m.processingUserSelection(0);
//		m.processingUserSelection(40);
//		BankAccount account = m.getAccount();
//		assertEquals(190, account.getBalance(), 0.01);
//	}

	@Test
	void testTerminateAccount() {
		Menu m = new Menu();
		m.createAccount("John");
		assertTrue(m.terminateAccount("John"));
	}
	@Test
	void testTerminateNonExistentAccount() {
		Menu m = new Menu();
		assertFalse(m.terminateAccount("John"));
	}
}
