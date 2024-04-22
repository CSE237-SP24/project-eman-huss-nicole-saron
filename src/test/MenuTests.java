
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	// need  more tests
	
	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		//user has provided value input of 50
		m.processingUserSelection(50);
		
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
	}
	
	@Test
	void testMultipleUserDeposit() {
		Menu m = new Menu();
		//user has provided value input of 50
		m.processingUserSelection(50);
		m.processingUserSelection(100);
		m.processingUserSelection(0);
		m.processingUserSelection(40);
		BankAccount account = m.getAccount();
		assertEquals(190, account.getBalance(), 0.01);
	}

}
/*********DEPOSIT TESTS************/
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
/*********WITHDRAW TESTS************/
//@BeforeEach
//void setup() {
//    testAccount = BankAccount.createAccount("test");
//}
//
//@Test
//void testNoWithdraw() {
//		assertEquals(0, testAccount.getBalance(), 0.01);
//}
//
//@Test
//void testSimpleWithdraw() {
//        //2. Call the method being tested
//        testAccount.withdraw(25);
//
//        //3. Use assertions to verify results
//        assertEquals(25.0, testAccount.getBalance(), 0.01);        
//}
//
//@Test
//void testAddingMultipleWithdraws() {
//        //2. Call the method being tested
//        testAccount.withdraw(10);
//        testAccount.withdraw(0);
//
//        //3. Use assertions to verify results
//        assertEquals(10.0, testAccount.getBalance(), 0.01);        
//}
//
//@Test
//void testNegativeWithdraw() {
//        //2. Call the method being tested
//        testAccount.withdraw(-25);
//
//        //3.
//        assertEquals(0, testAccount.getBalance(), 0.01);
//}
//
//@Test
//void testMulitpleWithdrawsWithANegative() {
//        //2. Call the method being tested
//		testAccount.withdraw(100);
//		testAccount.withdraw(-25);
//		//we expect to end up here, -25 is a bad input
//		assertEquals(100, testAccount.getBalance(), 0.01);
//}
//
//@Test
//void testLargeWithdraw() {
//        testAccount.withdraw(Math.pow(20, 308));
//        assertEquals(0, testAccount.getBalance(), 0.01);
//}
//
//@Test
//void testAccountOverflow() {
//        testAccount.withdraw(Double.MAX_VALUE);
//        testAccount.withdraw(Double.MAX_VALUE);
//        assertEquals(Double.MAX_VALUE, testAccount.getBalance(), 0.01);
//}
/*********TRANSFER TESTS************/
//@BeforeEach
//void setup() {
//    testAccount1 = BankAccount.createAccount("test1");
//    testAccount2 = BankAccount.createAccount("test2");
//    testAccount1.deposit(1000);
//}
//
//@Test
//void testTransferZero() {
//	 	//2. Call the method being tested
//		testAccount1.transfer(testAccount2, 0);
//		
//		//3. Use assertions to verify results
//	assertEquals(1000, testAccount1.getBalance(), 0.01);
//	assertEquals(0, testAccount2.getBalance(), 0.01);
//			
//}
//
//@Test
//void testSimpleTransfer() {
//		//2. Call the method being tested
//		testAccount1.transfer(testAccount2,25);
//		
//		//3. Use assertions to verify results
//		assertEquals(975, testAccount1.getBalance(), 0.01);
//		assertEquals(25, testAccount2.getBalance(), 0.01); 
//}
//
//@Test
//void testMultipleTransfers() {
//		//2. Call the method being tested
//		testAccount1.transfer(testAccount2,10);
//		testAccount1.transfer(testAccount2,90);
//		testAccount1.transfer(testAccount2,500);
//		
//		//3. Use assertions to verify results
//		assertEquals(400, testAccount1.getBalance(), 0.01); 
//		assertEquals(600, testAccount2.getBalance(), 0.01);
//}
//
//@Test
//void testBackandForthTransfers() {
//		//2. Call the method being tested
//		testAccount1.transfer(testAccount2,1000);
//		testAccount2.transfer(testAccount1,400);
//		
//		//3. Use assertions to verify results
//		assertEquals(400, testAccount1.getBalance(), 0.01); 
//		assertEquals(600, testAccount2.getBalance(), 0.01);
//}
//
//@Test
//void testNegativeTransfer() {
//		//2. Call the method being tested
//		testAccount1.transfer(testAccount2,-25);
//		
//		//3. Use assertions to verify results
//		assertEquals(1000, testAccount1.getBalance(), 0.01);
//		assertEquals(0, testAccount2.getBalance(), 0.01);
//}
//
//@Test
//void testSimpleOverTransfer() {
//		//2. Call the method being tested
//		testAccount1.transfer(testAccount2,2000);
//		
//		//3. Use assertions to verify results
//    assertEquals(1000, testAccount1.getBalance(), 0.01);
//    assertEquals(0, testAccount2.getBalance(), 0.01);
//}
//
//@Test
//void testSecondOverTransfer() {
//		//2. Call the method being tested
//    testAccount1.transfer(testAccount2,10);
//    testAccount2.transfer(testAccount1,15);
//    
//    //3. Use assertions to verify results
//		assertEquals(8990, testAccount1.getBalance(), 0.01);
//		assertEquals(19, testAccount2.getBalance(), 0.01);
//}
