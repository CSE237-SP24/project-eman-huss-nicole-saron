package bankapp;

public class TransferHandler {
	public void transfer(BankAccount sendingAccount, BankAccount receivingAccount, double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		} 
		if(amount >  receivingAccount.getBalance()) {
			throw new IllegalArgumentException("You are overdrafting your account. Your balance is: " + sendingAccount.getBalance() + ". Please transfer an amount less than or equal to your balance.");
		}
		sendingAccount.removeBalance(amount);
		receivingAccount.addBalance(amount);
	}
}
