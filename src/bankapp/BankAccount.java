package bankapp;

public class BankAccount {
	
	private double balance;
	private String accountName = "";
	
	// Constructor with an account name
	public BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;
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
