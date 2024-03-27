
package bankapp;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

	private double balance; // we can add balance to be part of the constructor
	private String accountName = "";
	private DepositHandler depositHandler;
	private WithdrawHandler withdrawHandler;
	private TransferHandler transferHandler;
    private static Map<String, BankAccount> allAccounts = new HashMap<>();
    
	// -- Constructors are private because they shouldn't be called on from the
	// outside, call the createAccount method instead
	// Constructor with an account name
	private BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;

		this.depositHandler = new DepositHandler();
    	this.withdrawHandler = new WithdrawHandler();
		this.transferHandler = new TransferHandler();
	}

	public String getAccountName() {
		return this.accountName;
	}

	public double getBalance() {
		return this.balance;
	}

	public void addBalance(double amount) {
		this.balance += amount;
	}
	public void removeBalance(double amount) {
		this.balance -= amount;
	}

	// Factory method for the constructor with the name
	// -- This and the other createAccount method would be the one to use when
	// making an account
	public static BankAccount createAccount(String name) {
		if (name == null || name.isEmpty()) {
			System.err.print("FATAL ERROR: name cannot be null !!");
		}

		if (allAccounts.containsKey(name)) {
			System.err.println("This account name is already taken");
			return null;
		}
		BankAccount account = new BankAccount(name);
		allAccounts.put(name, account);
		return account;
	}

	// TODO: TEMP: remove these: Factory method for the constructor without a name
	public static BankAccount createAccount() {
		// these temporary nameless accounts wont be added to the allAccountNames set
		return new BankAccount(null);
	}

	public void deposit(double amount){
	 	depositHandler.depositInAccount(this, amount);
	}
	public void transfer(BankAccount receivingAccount, double amount){
	 	transferHandler.transfer(this, receivingAccount,amount);
	}
	
    public static BankAccount getAccountByName(String name) {
        return allAccounts.get(name);
    }
}

