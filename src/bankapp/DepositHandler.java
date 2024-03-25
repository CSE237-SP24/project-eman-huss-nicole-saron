package bankapp;

public class DepositHandler {

	public void depositInAccount(BankAccount account, double amount) {

		if (!transactionErrorHandler(account, amount)) {
			return;
		}
		account.addBalance(amount);
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
}
