package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class WithdrawHandlerTests {
	private BankAccount testAccount; 
	
	@BeforeEach
	void setup() {
        testAccount = BankAccount.createAccount("test");
	}

    @Test
    void testNoWithdraw() {
			assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testSimpleWithdraw() {
            //2. Call the method being tested
            testAccount.withdraw(25);

            //3. Use assertions to verify results
            assertEquals(25.0, testAccount.getBalance(), 0.01);        
    }

    @Test
    void testAddingMultipleWithdraws() {
            //2. Call the method being tested
            testAccount.withdraw(10);
            testAccount.withdraw(0);

            //3. Use assertions to verify results
            assertEquals(10.0, testAccount.getBalance(), 0.01);        
    }

    @Test
    void testNegativeWithdraw() {
            //2. Call the method being tested
            testAccount.withdraw(-25);

            //3.
            assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testMulitpleWithdrawsWithANegative() {
            //2. Call the method being tested
    		testAccount.withdraw(100);
    		testAccount.withdraw(-25);
    		//we expect to end up here, -25 is a bad input
    		assertEquals(100, testAccount.getBalance(), 0.01);
    }

    @Test
    void testLargeWithdraw() {
            testAccount.withdraw(Math.pow(20, 308));
            assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testAccountOverflow() {
            testAccount.withdraw(Double.MAX_VALUE);
            testAccount.withdraw(Double.MAX_VALUE);
            assertEquals(Double.MAX_VALUE, testAccount.getBalance(), 0.01);
    }

}
