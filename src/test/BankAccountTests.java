package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {
	private BankAccount sendingAccount;
	private BankAccount receivingAccount;

	@BeforeEach
	void setUp() {
		sendingAccount = new BankAccount();
		receivingAccount = new BankAccount();
	}
	@Test
	void testSimpleDeposit() {
		//1. Setup Objects
		
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}
	@Test
	void testTransfer() {
		
		sendingAccount.deposit(200);
		sendingAccount.transfer(receivingAccount, 100);
		
		assertEquals(100, sendingAccount.getBalance(), 0.01);	
		assertEquals(100, receivingAccount.getBalance(), 0.01);	
		
		
	}
	@Test
	void testTransferMaxOut() {
		try {
			sendingAccount.deposit(50);
			sendingAccount.transfer(receivingAccount, 100);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}	
		
		
	}
	@Test
	void testTransfeNegativeAmount() {
		
		try {
			sendingAccount.deposit(500);
			sendingAccount.transfer(receivingAccount, -100);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -100 is a bad input
			assertTrue(true);
		}	
		
		
	}

}
