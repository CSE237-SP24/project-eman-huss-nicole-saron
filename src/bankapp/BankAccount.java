
package bankapp;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

	private double balance;
	private String accountName = "";
	private static Map<String, BankAccount> allAccounts = new HashMap<>();

	// Constructor is private because they shouldn't be called on from the
	// outside, call the createAccount method instead
	private BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;
	}

	public static BankAccount getAccountByName(String name) {
		return allAccounts.get(name);
	}

	public String getAccountName() {
		return this.accountName;
	}

	public double getBalance() {
		return this.balance;
	}

	public boolean addBalance(BankAccount account, double amount) {
		if (!addingBalanceErrorHandler(account, amount)) {
			return false;
		}
		this.balance += amount;
		return true;
	}

	public boolean removeBalance(double amount) {
		if (!removingBalanceErrorHandler(amount)) {
			return false;
		}
		this.balance -= amount;
		return true;
	}

	// Factory method for the constructor with the name
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

	public void deposit(double amount) {
		if (!this.addBalance(this, amount)) {
			System.err.println("failed to deposit moneys");
			return;
		}
		System.out.println("deposit successfull");
	}

	public void withdraw(double amount) {
		if (!this.removeBalance(amount)) {
			System.err.println("failed to withdraw");
			return;
		}
		System.out.println("withdraw succeeded");
	}

	public void transfer(BankAccount receivingAccount, double amount) {
		if (!this.removeBalance(amount)) {
			System.err.println("transfer failed: an error in your balance");
			return;
		}

		if (receivingAccount == null || receivingAccount.accountName.isBlank()) {
			System.err.println("transfer failed: receiving account not found :(");
			return;
		}

		if (!receivingAccount.addBalance(receivingAccount, amount)) {
			System.err.println("transfer failed: an error in the receivingAccount's balance");
		}

		System.out.println("transfer success");
	}

	// ******************** TRANSACTION ERROR HANDLERS ********************//
	private boolean removingBalanceErrorHandler(double amount) {
		if (amount <= 0 || amount == Double.NEGATIVE_INFINITY) {
			System.err.println("Amount too small, must be positive");
			return false;
		} else if ((this.getBalance() - amount < 0) || amount == Double.POSITIVE_INFINITY) {
			System.err.println("Withdraw account exceeds balance");
			return false;
		}
		return true;
	}

	private boolean addingBalanceErrorHandler(BankAccount account, double amount) {
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

}
