package bankapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private static boolean exit = false;
	private static Map<String, BankAccount> allAccounts = new HashMap<>();
	
	public BankAccount getAccountByName(String name) {
		return allAccounts.get(name);
	}
	
	// not tested
	public static void main(String[] args) throws FileNotFoundException {

		// test account to transfer money to
		File file = new File("./file.txt");

		Menu mainMenu = new Menu();
		mainMenu.createAccount("MenuTestAccount");
//		mainMenu.readData(file);
		while (!exit) {
			mainMenu.displayingOptions();
			int task = mainMenu.getValidTaskInput();
			mainMenu.processingUserSelection(task);
//			mainMenu.writeData(file);
		}

	}
	
	public int getAccountHash(BankAccount account) {
		return (account.getAccountName()).hashCode();
	}
	
	// Factory method for the constructor with the name
	public BankAccount createAccount(String name) {
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
	
	// TODO:!!!! change to private later
	public void writeData(File f, BankAccount acc) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(f);
		out.println(acc);
		out.close();

	}

//	order of account info: username, balance, (then put account type in iteration 3)

	private void readData(File f) throws FileNotFoundException {
		// Scanner in = new Scanner (inFile);
		String name = in.next();
		double balance = in.nextDouble();

	}

	// Constructor
	public Menu() {
		System.out.print("Enter your username:");
		this.in = new Scanner(System.in);
		this.account = createAccount(in.next());
	}

	// Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Enter the number of your desired action: 1) Transactions 2)Account");
		System.out.println("Enter the number of your desired action: "
				+ "\n 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Account\n 5) exit\n");
		System.out.println("Enter the number of your desired action: "
				+ "\n 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Account\n 5) exit\n");
	}
//	first display their original options, then based on that first input, present the options related to the next one
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
			processingWithdraw();
			break;
		case 3:
			processingTransfer();
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
		BankAccount receivingAccount = getAccountByName(receivingAccountName);
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
		System.out.println(account.getAccountName());
		return account;
	}
}
