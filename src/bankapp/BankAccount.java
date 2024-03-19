package bankapp;

// a hash set doesnt allow duplicates and doesnt maintain insertion order
import java.util.HashSet;

public class BankAccount {
	
	private double balance;
	private String accountName = "";
	HashSet<String> allAccountNames = new HashSet<String>();

	
	// Constructor with an account name
	public BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;
		
		if (!allAccountNames.add(name)) {
			// throw an error duplicate name
		}
		
	}
	
	// Constructor without an account name
	public BankAccount() {
		this.balance = 0;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
}
