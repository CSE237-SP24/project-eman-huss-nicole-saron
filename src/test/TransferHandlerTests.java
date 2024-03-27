package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class TransferHandlerTests {
	private BankAccount testAccount1;
	private BankAccount testAccount2; 
	
	@BeforeEach
	void setup() {
        testAccount1 = BankAccount.createAccount("test1");
        testAccount2 = BankAccount.createAccount("test2");
        testAccount1.deposit(1000);
	}

    @Test
    void testTransferZero() {
    	 	//2. Call the method being tested
    		testAccount1.transfer(testAccount2, 0);
    		
    		//3. Use assertions to verify results
		assertEquals(1000, testAccount1.getBalance(), 0.01);
		assertEquals(0, testAccount2.getBalance(), 0.01);
    			
    }

    @Test
    void testSimpleTransfer() {
    		//2. Call the method being tested
    		testAccount1.transfer(testAccount2,25);
    		
    		//3. Use assertions to verify results
    		assertEquals(975, testAccount1.getBalance(), 0.01);
    		assertEquals(25, testAccount2.getBalance(), 0.01); 
    }

    @Test
    void testMultipleTransfers() {
    		//2. Call the method being tested
    		testAccount1.transfer(testAccount2,10);
    		testAccount1.transfer(testAccount2,90);
    		testAccount1.transfer(testAccount2,500);
    		
    		//3. Use assertions to verify results
    		assertEquals(400, testAccount1.getBalance(), 0.01); 
    		assertEquals(600, testAccount2.getBalance(), 0.01);
    }
    
    @Test
    void testBackandForthTransfers() {
    		//2. Call the method being tested
    		testAccount1.transfer(testAccount2,1000);
    		testAccount2.transfer(testAccount1,400);
    		
    		//3. Use assertions to verify results
    		assertEquals(400, testAccount1.getBalance(), 0.01); 
    		assertEquals(600, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testNegativeTransfer() {
    		//2. Call the method being tested
    		testAccount1.transfer(testAccount2,-25);
    		
    		//3. Use assertions to verify results
    		assertEquals(1000, testAccount1.getBalance(), 0.01);
    		assertEquals(0, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testSimpleOverTransfer() {
    		//2. Call the method being tested
    		testAccount1.transfer(testAccount2,2000);
    		
    		//3. Use assertions to verify results
        assertEquals(1000, testAccount1.getBalance(), 0.01);
        assertEquals(0, testAccount2.getBalance(), 0.01);
    }
    
    @Test
    void testSecondOverTransfer() {
    		//2. Call the method being tested
        testAccount1.transfer(testAccount2,10);
        testAccount2.transfer(testAccount1,15);
        
        //3. Use assertions to verify results
    		assertEquals(8990, testAccount1.getBalance(), 0.01);
    		assertEquals(19, testAccount2.getBalance(), 0.01);
    }
}
