package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {
<<<<<<< HEAD
=======
	
	@Test
	void testNoDeposit() {
		//1. Setup Objects
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		
		//3. Use assertions to verify results
		assertEquals(0, testAccount.getBalance(), 0.01);
	}
>>>>>>> deposit

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
<<<<<<< HEAD
=======
	void testAddingMultipleDeposits() {
		//1. Setup Objects
		BankAccount testAccount = new BankAccount();
				
		//2. Call the method being tested
		testAccount.deposit(10);
		testAccount.deposit(0);
				
		//3. Use assertions to verify results
		assertEquals(10.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
>>>>>>> deposit
	void testNegativeDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
<<<<<<< HEAD
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
=======
			testAccount.deposit(-25);

		//3.
			assertEquals(0, testAccount.getBalance(), 0.01);
	}
	
	@Test
	void testMulitpleDepositsWithANegative() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
			testAccount.deposit(100);
			testAccount.deposit(-25);
			//we expect to end up here, -25 is a bad input
			assertEquals(100, testAccount.getBalance(), 0.01);
	}
	
	@Test
	void testLargeDeposit() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(Math.pow(20, 308));
		assertEquals(0, testAccount.getBalance(), 0.01);
	}
	
	@Test
	void testAccountOverflow() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(Double.MAX_VALUE);
		testAccount.deposit(Double.MAX_VALUE);
		assertEquals(Double.MAX_VALUE, testAccount.getBalance(), 0.01);
>>>>>>> deposit
	}

}
