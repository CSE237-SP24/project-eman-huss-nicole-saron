package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class TerminateAccountTests {
	private BankAccount testAccount; 
	
	@BeforeEach
	void setup() {
		Menu mainMenu = new Menu();
        testAccount = mainMenu.createAccount("test");
	}

    @Test
    void testNoDeposit() {
			assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testSimpleDeposit() {
            //2. Call the method being tested
            testAccount.deposit(25);

            //3. Use assertions to verify results
            assertEquals(25.0, testAccount.getBalance(), 0.01);        
    }

    @Test
    void testAddingMultipleDeposits() {
            //2. Call the method being tested
            testAccount.deposit(10);
            testAccount.deposit(0);

            //3. Use assertions to verify results
            assertEquals(10.0, testAccount.getBalance(), 0.01);        
    }

    @Test
    void testNegativeDeposit() {
            //2. Call the method being tested
            testAccount.deposit(-25);

            //3.
            assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testMulitpleDepositsWithANegative() {
            //2. Call the method being tested
    		testAccount.deposit(100);
    		testAccount.deposit(-25);
    		//we expect to end up here, -25 is a bad input
    		assertEquals(100, testAccount.getBalance(), 0.01);
    }

    @Test
    void testLargeDeposit() {
            testAccount.deposit(Math.pow(20, 308));
            assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testAccountOverflow() {
            testAccount.deposit(Double.MAX_VALUE);
            testAccount.deposit(Double.MAX_VALUE);
            assertEquals(Double.MAX_VALUE, testAccount.getBalance(), 0.01);
    }

}
