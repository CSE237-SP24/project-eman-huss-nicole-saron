package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if (!transactionErrorHandler(amount)) {
			return;
		}
		this.balance+= amount;
	}

	private boolean transactionErrorHandler(double amount) {
		if(amount < 0) {
			System.err.println("Amount must be positive");
			return false;
		}
		else if (getBalance()+amount<getBalance()) {
			System.err.println("Account is too full");
			return false;
		}
		else if(amount == Double.POSITIVE_INFINITY) {
			System.err.println("Deposit is too large. Add a smaller deposit.");
			return false;
		}
		else if (getBalance()+amount==Double.POSITIVE_INFINITY) {
			System.err.println("Account overflowed. Add a smaller deposit.");
			return false;
		}
		//unclear on how to test this, but just to be safe
		else if(getBalance()+amount == Double.NEGATIVE_INFINITY 
				||getBalance()==Double.NEGATIVE_INFINITY) {
			System.err.println("Amount must be positive.");
			return false;
		}
		return true;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
}
