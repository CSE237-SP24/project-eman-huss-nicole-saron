package bankapp;

import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private LinkedList<BankAccount> accounts;
	
	//not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.readData();
		mainMenu.displayingOptions();
		int task = mainMenu.getValidTaskInput();
		mainMenu.processingUserSelection(task);
		mainMenu.writeData();
	}
	
	private void writeData() {
		// TODO create File, creat PrintWriter, iterate through all bankaccounts and write them. RandomAccessFile - -requires more care with formatting
		
	}

	private void readData() {
		// TODO create File, Create scanner, read in data and create BankAccount 
		
	}

	//Constructor
	public Menu() {
		System.out.println("Enter your name:");
		this.in = new Scanner(System.in);
		this.account = BankAccount.createAccount("in");
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Enter the number of your desired action: 1) Deposit 2) Withdraw 3) Transfer 4) Account");
	}
	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	
	public int getValidTaskInput() {
		int task = in.nextInt();
		while(task < 0) {
			System.out.println("Invalid value!");
			System.out.println("Enter the number of your desired action: 1) Deposit 2) Withdraw 3) Transfer 4) Account");
			task = in.nextInt();
		}
		return task;
	}
	public double validMoneyInput() {
		double amount = in.nextDouble();
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}
	
	//Does work - needs tests
	public void processingUserSelection(int task) {
		switch(task) {
		case 1:
			processingDeposit();
			break;
		case 2:
			processingTransfer();
			break;
		case 3:
			processingWithdraw();
			break;
		case 4:	
			getAccount();
			break;
		}
		
			
	}
	public void processingDeposit() {
		double amount = validMoneyInput();
		account.deposit(amount);
<<<<<<< HEAD
		System.out.println("Your balance is now: " + account.getBalance());
		
	}
	public void processingTransfer() {
		System.out.println("Enter the account to transfer to");
		String receivingAccount = in.next();
		double amount = validMoneyInput();
//		account.transfer(receivingAccount, amount);
	}
	public void processingWithdraw() {
		
=======
		System.out.println("Your balance now is: $" + account.getBalance());
>>>>>>> eb1d933bbff19e2dc491c3ea300a6a3ef7b829c6
	}
	public BankAccount getAccount() {
		return account;
	}
}
