package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@BeforeEach
	void setUp() {
		BankAccount testAccount = BankAccount.createAccount("TestAccount");
		BankAccount testAccount2 = BankAccount.createAccount("TestAccount2");
	}
	
	@Test
	void testSimpleDeposit() {
		//1. Setup Objects
		BankAccount johnAccount = BankAccount.createAccount("John");
		
		//2. Call the method being tested
		johnAccount.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, johnAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = BankAccount.createAccount();
		
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
		BankAccount sendingAccount = new BankAccount();
		BankAccount receivingAccount = new BankAccount();
		
		sendingAccount.deposit(200);
		sendingAccount.transfer(receivingAccount, 100);
		
		assertEquals(100, sendingAccount.getBalance(), 0.01);	
		assertEquals(100, receivingAccount.getBalance(), 0.01);	
		
		
	}
	void testTransferMaxOut() {
		BankAccount sendingAccount = new BankAccount();
		BankAccount receivingAccount = new BankAccount();
		
		sendingAccount.deposit(200);
		sendingAccount.transfer(receivingAccount, 100);
		
		assertEquals(100, sendingAccount.getBalance(), 0.01);	
		assertEquals(100, receivingAccount.getBalance(), 0.01);	
		
		
	}

}
