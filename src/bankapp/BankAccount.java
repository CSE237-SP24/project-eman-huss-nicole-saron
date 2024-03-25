package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
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
	public void transfer(BankAccount receivingAccount, double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		} 
		if(amount > this.balance) {
			throw new IllegalArgumentException("You are overdrafting your account. Your balance is: " + this.balance + ". Please transfer an amount less than or equal to your balance.");
		}
		this.balance = this.balance - amount;
		receivingAccount.balance += amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
}
