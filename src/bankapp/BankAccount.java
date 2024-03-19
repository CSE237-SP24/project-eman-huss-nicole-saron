package bankapp;

// a hash set doesnt allow duplicates and doesnt maintain insertion order
import java.util.HashSet;

public class BankAccount {
	
	private double balance;
	private String accountName = "";
	private HashSet<String> allAccountNames = new HashSet<String>();
	
	// Constructor with an account name
	public BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;
	}
	
	// Constructor without an account name
	public BankAccount() {
		this.balance = 0;
	}

	public String getAccountName() {
		return this.accountName;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	// Factory method for the constructor with the name
	public BankAccount createAccount(String name) {
		// handles duplicate name
		if (!allAccountNames.add(name)) {
			System.err.println("This account name is already taken");
			return null;
		}
		return new BankAccount(name);
	}
	
	// Factory method for the constructor without a name
	public BankAccount createAccount() {
		return new BankAccount();
	}
	
	public HashSet<String> getAllAccountNames() {
		return allAccountNames;
	}
	
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
}
