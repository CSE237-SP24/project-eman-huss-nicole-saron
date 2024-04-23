package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class BankAccountTests {
    private BankAccount testAccount;
    private BankAccount testAccount1;
    private BankAccount testAccount2;
    private Menu mainMenu;

    @BeforeEach
    void setup() throws IOException {
    	mainMenu = new Menu();
    	mainMenu.clearAllAccountInfo();
        testAccount = mainMenu.createAccount("test");
        testAccount1 = mainMenu.createAccount("test1");
        testAccount2 = mainMenu.createAccount("test2");
        testAccount1.deposit(1000);
    }

    // Deposit tests

    @Test
    void testNoDeposit() {
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testSimpleDeposit() {
        testAccount.deposit(25);
        assertEquals(25.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testAddingMultipleDeposits() {
        testAccount.deposit(10);
        testAccount.deposit(0);
        assertEquals(10.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testNegativeDeposit() {
        testAccount.deposit(-25);
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testMultipleDepositsWithANegative() {
        testAccount.deposit(100);
        testAccount.deposit(-25);
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

    // Account tests

    @Test
    void testGetAccountName() {
        BankAccount johnAccount = mainMenu.createAccount("John2");
        String accountName = johnAccount.getAccountName();
        assertEquals("John2", accountName);
    }

    @Test
    void testGetAccountByName() {
        BankAccount johnAccount = mainMenu.createAccount("John3");
        BankAccount maryAccount = mainMenu.createAccount("Mary");
        BankAccount retrievedAccount = mainMenu.getAccountByName("John3");
        assertNotNull(retrievedAccount);
        assertEquals("John3", retrievedAccount.getAccountName());
        assertEquals(johnAccount, retrievedAccount);
    }

    @Test
    void testGetAccountByNameNonExistent() {
        BankAccount johnAccount = mainMenu.createAccount("John4");
        BankAccount maryAccount = mainMenu.createAccount("Mary");
        BankAccount retrievedAccount = mainMenu.getAccountByName("NonExistentAccount");
        assertNull(retrievedAccount);
    }

    // Transfer tests

    @Test
    void testTransferZero() {
        testAccount1.transfer(testAccount2, 0);
        assertEquals(1000, testAccount1.getBalance(), 0.01);
        assertEquals(0, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testSimpleTransfer() {
        testAccount1.transfer(testAccount2, 25);
        assertEquals(975, testAccount1.getBalance(), 0.01);
        assertEquals(25, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testMultipleTransfers() {
        testAccount1.transfer(testAccount2, 10);
        testAccount1.transfer(testAccount2, 90);
        testAccount1.transfer(testAccount2, 500);
        assertEquals(400, testAccount1.getBalance(), 0.01);
        assertEquals(600, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testBackAndForthTransfers() {
        testAccount1.transfer(testAccount2, 1000);
        testAccount2.transfer(testAccount1, 400);
        assertEquals(400, testAccount1.getBalance(), 0.01);
        assertEquals(600, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testNegativeTransfer() {
        testAccount1.transfer(testAccount2, -25);
        assertEquals(1000, testAccount1.getBalance(), 0.01);
        assertEquals(0, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testSimpleOverTransfer() {
        testAccount1.transfer(testAccount2, 2000);
        assertEquals(1000, testAccount1.getBalance(), 0.01);
        assertEquals(0, testAccount2.getBalance(), 0.01);
    }

    @Test
    void testSecondOverTransfer() {
        testAccount1.transfer(testAccount2, 990);
        testAccount2.transfer(testAccount1, 1000);
        assertEquals(10, testAccount1.getBalance(), 0.01);
        assertEquals(990, testAccount2.getBalance(), 0.01);
    }

    // Withdraw tests

    @Test
    void testNoWithdraw() {
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testSimpleWithdraw() {
        testAccount.deposit(100);
        testAccount.withdraw(25);
        assertEquals(75.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testAddingMultipleWithdraws() {
        testAccount.deposit(100);
        testAccount.withdraw(10);
        testAccount.withdraw(20);
        assertEquals(70.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testNegativeWithdraw() {
        testAccount.deposit(100);
        testAccount.withdraw(-25);
        assertEquals(100, testAccount.getBalance(), 0.01);
    }

    @Test
    void testMultipleWithdrawsWithANegative() {
        testAccount.deposit(100);
        testAccount.withdraw(50);
        testAccount.withdraw(-25);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    void testLargeWithdraw() {
        testAccount.deposit(100);
        testAccount.withdraw(Math.pow(20, 308));
        assertEquals(100, testAccount.getBalance(), 0.01);
    }

    @Test
    void testAccountUnderflow() {
        testAccount.deposit(100);
        testAccount.withdraw(Double.MAX_VALUE);
        assertEquals(100, testAccount.getBalance(), 0.01);
    }
}