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
	private static File file = new File("./file.txt");
	public BankAccount getAccountByName(String name) {
		return allAccounts.get(name);
	}
	
	// not tested
	public static void main(String[] args) throws FileNotFoundException {

		// test account to transfer money to

		Menu mainMenu = new Menu();
		mainMenu.readData(file);
		while (!exit) {
			mainMenu.displayingLoginOptions();
			int task = mainMenu.getValidTaskInput();
			mainMenu.processingUserSelection(task);
//			mainMenu.writeData(file);
		}

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
		Scanner in = new Scanner (f);
		while (in.hasNextLine()) {
			String name = in.next();
			double balance = in.nextDouble();
			BankAccount account = new BankAccount(name); 
			account.deposit(balance);
			allAccounts.put(name, account);
		}
		
		in.close();
	}

	// Constructor
	public Menu() {
//		System.out.print("Enter your username:");
		this.in = new Scanner(System.in);
//		this.account = createAccount(in.next());
	}
	
//	login or sign up
	public void displayingLoginOptions() {
		System.out.println("If you have an account with us, please press one. Otherwise, press two.: 1) Log in 2)Sign up");
		int logininput = in.nextInt();
		if (logininput == 1) { //have an account and are signing in
			System.err.println("Welcome back! Please enter username.");
			account = allAccounts.get(in.next());
		} else {
			System.err.println("Welcome! Please create an account by providing a username.");
			BankAccount newAccount = createAccount(in.next());
			allAccounts.put(newAccount.getAccountName(), newAccount);
//			TODO for next meeting: call writeData here to update file with new account?
			account = allAccounts.get(newAccount.getAccountName());
		}
		displayingAccountOptions();
	}
	// Code that just displays stuff - no tests needed
	public void displayingAccountOptions() {
		System.out.println("Enter the number of your desired action: "
				+ "\n 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Account Settings\n 5) Exit\n");
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
					displayingAccountOptions();
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Invalid input! Please enter an integer.");
				in.nextLine(); // Clear the invalid input from the scanner
				displayingAccountOptions();
			}
		}

		return task;
	}

//	TODO for next meeting: do we need this function if deposit function already accounts for invalid inputs?
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
			accountSettings();
			break;
		case 5:
			exit();
			break;
		}
	}

	public void exit() {
		System.out.println("exiting...");
		exit = true;
		System.out.println("Exited. See you soon!");
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
		System.out.println("Enter how much to withdraw in cash:");
		int withdrawAmount = in.nextInt();
		account.withdraw(withdrawAmount);
		System.out.println("Your balance now is: $" + account.getBalance());
	}

	public void accountSettings() {
		System.out.println("Enter the action you'd like: 1) View account information 2) Change username 3) Terminate account");
		int option = in.nextInt();
		switch (option) {
		case 1:
			getAccount();
			break;
		case 2:
			terminateAccount(account.getAccountName());
			break;
		case 3:
			changeUsername();
			break;
		}
	}
	public void getAccount() {
		System.out.println(account.getAccountName());
		System.out.println(account.getBalance());
	}
	public boolean terminateAccount(String accountName) {
		if (allAccounts.containsKey(accountName)) {
			allAccounts.remove(accountName);
			return true;
		}
		System.err.println("Account not found.");
		return false;
	}
	public void changeUsername() {
		
	}
}
