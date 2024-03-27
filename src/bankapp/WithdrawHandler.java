package bankapp;

public class WithdrawHandler {

    public void withdrawInAccount(BankAccount account, double amount) {
		if (!withdrawErrorHandler(account, amount)) {
			return;
		}
		account.removeBalance(amount);
	}

    //need to add: amount with more than two decimal places? ex 10.576
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

