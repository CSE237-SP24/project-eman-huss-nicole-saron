
package bankapp;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

	private double balance; // we can add balance to be part of the constructor
	private String accountName = "";
//	private DepositHandler depositHandler;
	//private WithdrawHandler withdrawHandler;
	//private TransferHandler transferHandler;
    private static Map<String, BankAccount> allAccounts = new HashMap<>();
    
	// -- Constructors are private because they shouldn't be called on from the
	// outside, call the createAccount method instead
	// Constructor with an account name
	private BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;

//		this.depositHandler = new DepositHandler();
//    	this.withdrawHandler = new WithdrawHandler();
		//this.transferHandler = new TransferHandler();
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
		if (!transactionErrorHandler(this, amount)) {
			return;
		}
		this.addBalance(amount);
	}
	private boolean transactionErrorHandler(BankAccount account, double amount) {
		if (amount < 0) {
			System.err.println("Amount must be positive");
			return false;
		} else if (account.getBalance() + amount < account.getBalance()) {
			System.err.println("Account is too full");
			return false;
		} else if (amount == Double.POSITIVE_INFINITY) {
			System.err.println("Deposit is too large. Add a smaller deposit.");
			return false;
		} else if (account.getBalance() + amount == Double.POSITIVE_INFINITY) {
			System.err.println("Account overflowed. Add a smaller deposit.");
			return false;
		}
		// unclear on how to test this, but just to be safe
		else if (account.getBalance() + amount == Double.NEGATIVE_INFINITY
				|| account.getBalance() == Double.NEGATIVE_INFINITY) {
			System.err.println("Amount must be positive.");
			return false;
		}
		return true;

	}
	public void transfer(BankAccount receivingAccount, double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		} 
		if(amount >  receivingAccount.getBalance()) {
			throw new IllegalArgumentException("You are overdrafting your account. Your balance is: " + this.getBalance() + ". Please transfer an amount less than or equal to your balance.");
		}
		/*
		 * if (!this.removeBalance(amount)
		 * 	return amount is invalid
		 * 
		 * if(receivingAccount ==null)		
		 * 	return receivingAccount Not found	
		 * 
		 * if (!receivingAccount.addBalance(amount)
		 * 	return amount is invalid
		 *  
		 * return success
		 * 
		 */
		
		// changethe rempve and add
		this.removeBalance(amount);
		receivingAccount.addBalance(amount);
	}
	
    public static BankAccount getAccountByName(String name) {
        return allAccounts.get(name);
    }
    
    public void withdraw(double amount){
    	if (!withdrawErrorHandler(this, amount)) {
			return;
		}
		this.removeBalance(amount);
	}
    private boolean withdrawErrorHandler(BankAccount account, double amount) {
		if (amount <= 0 || amount == Double.NEGATIVE_INFINITY) {
			System.err.println("Amount too small, must be positive");
			return false;
		} else if ((account.getBalance() - amount < 0) || amount == Double.POSITIVE_INFINITY) {
			System.err.println("Withdraw account exceeds balance");
			return false;
		} 
		return true;
    }
}

