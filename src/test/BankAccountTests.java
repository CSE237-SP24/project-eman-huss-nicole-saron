//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import bankapp.BankAccount;
//import bankapp.Menu;
//
//class BankAccountTests {
//
//	@BeforeEach
//	void setup() {
//		Menu mainMenu = new Menu();
//	}
//	
//	@Test
//	void testSimpleDeposit() {
//		//1. Setup Objects
//		BankAccount johnAccount = mainMenu.createAccount("John1");
//		
//		//2. Call the method being tested
//		johnAccount.deposit(25);
//		
//		//3. Use assertions to verify results
//		assertEquals(25.0, johnAccount.getBalance(), 0.01);	
//	}
//	
//	
//    @Test
//    void testGetAccountName() {
//        // 1. Setup Objects
//        BankAccount johnAccount = BankAccount.createAccount("John2");
//
//        // 2. Call the method being tested
//        String accountName = johnAccount.getAccountName();
//
//        // 3. Use assertions to verify results
//        assertEquals("John2", accountName);
//    }
//
//    @Test
//    void testGetAccountByName() {
//        // 1. Setup Objects
//        BankAccount johnAccount = BankAccount.createAccount("John3");
//        BankAccount maryAccount = BankAccount.createAccount("Mary");
//
//        // 2. Call the method being tested
//        BankAccount retrievedAccount = BankAccount.getAccountByName("John3");
//
//        // 3. Use assertions to verify results
//        assertNotNull(retrievedAccount);
//        assertEquals("John3", retrievedAccount.getAccountName());
//        assertEquals(johnAccount, retrievedAccount);
//    }
//
//    @Test
//    void testGetAccountByNameNonExistent() {
//        // 1. Setup Objects
//        BankAccount johnAccount = BankAccount.createAccount("John4");
//        BankAccount maryAccount = BankAccount.createAccount("Mary");
//
//        // 2. Call the method being tested
//        BankAccount retrievedAccount = BankAccount.getAccountByName("NonExistentAccount");
//
//        // 3. Use assertions to verify results
//        assertNull(retrievedAccount);
//    }
//
//}
