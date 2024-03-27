package bankapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private static boolean exit = false;
	
	// not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.readData();
		while(!exit) {			
			mainMenu.displayingOptions();
			int task = mainMenu.getValidTaskInput();
			mainMenu.processingUserSelection(task);
			mainMenu.writeData();
		}
		
	}

	private void writeData() {
		// TODO create File, creat PrintWriter, iterate through all bankaccounts and
		// write them. RandomAccessFile - -requires more care with formatting

	}

	private void readData() {
		// TODO create File, Create scanner, read in data and create BankAccount

	}

	// Constructor
	public Menu() {
		System.out.print("Enter your name:");
		this.in = new Scanner(System.in);
		this.account = BankAccount.createAccount(in.next());
	}

	// Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Enter the number of your desired action: \n 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Account\n 5) exit\n");
	}
	// Code that gets user input
	// No tests needed...for now (probably discuss in future class)

	public int getValidTaskInput() {
	    int task = 0;
	    boolean validInput = false;
	    
	    while (!validInput) {
	        try {
	            task = in.nextInt();
	            if (task < 0 || task > 5) {
	                System.err.println("Invalid value!");
	                displayingOptions();
	            } else {
	                validInput = true;
	            }
	        } catch (InputMismatchException e) {
	            System.err.println("Invalid input! Please enter an integer.");
	            in.nextLine(); // Clear the invalid input from the scanner
	            displayingOptions();
	        }
	    }
	    
	    return task;
	}


	public double validMoneyInput() {
		System.out.println("How much?");
		double amount = in.nextDouble();
		while (amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}

	// Does work - needs tests
	public void processingUserSelection(int task) {
		switch (task) {
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
		case 5:
			exit();
			break;
		}
	}
	
	public void exit() {
		System.out.println("exiting...");
		exit = true;
	}

	public void processingDeposit() {
		double amount = validMoneyInput();
		account.deposit(amount);
		System.out.println("Your balance now is: $" + account.getBalance());

	}

	public void processingTransfer() {
	    System.out.println("Enter the name of the account to transfer to");
	    String receivingAccountName = in.next();
	    BankAccount receivingAccount = BankAccount.getAccountByName(receivingAccountName);
	    if (receivingAccount == null) {
	        System.out.println("Invalid account name. Transfer failed.");
	        return;
	    }
	    double amount = validMoneyInput();
	    account.transfer(receivingAccount, amount);
	}

	public void processingWithdraw() {
	}

	public BankAccount getAccount() {
		return account;
	}
}
