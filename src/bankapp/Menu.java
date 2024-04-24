package bankapp;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private static boolean exit = false;
	private FileData fileData;
	private String path = "./file.txt";

	public Menu() {
		this.in = new Scanner(System.in);
		this.fileData = new FileData(path, 80);
	}


	public long getAccountHash(BankAccount account) {
		return ((account.getAccountName()).hashCode());
	}
	public BankAccount getAccountByName(String accountName) {
		BigInteger recordNumber = BigInteger.valueOf(accountName.hashCode());
		recordNumber = recordNumber.and(BigInteger.valueOf(Long.MAX_VALUE));

		try {
			String readData = fileData.readData(recordNumber);
			if (readData.isBlank()) {
				System.out.println("Account not found: " + accountName);
				return null;
			}
			String[] parts = readData.split(";");
			if (parts.length >= 2) {
				accountName = parts[0];
				Double balance = Double.parseDouble(parts[1]);
				account = createAccount(accountName);
				account.addBalance(account, balance);
				return account;
			} else {
				System.out.println("Invalid account data format");
				return null;
			}
		} catch (IOException e) {
			System.err.println("An error occurred while reading the account data: " + e.getMessage());
			return null;
		}
	}


	public static void main(String[] args) throws IOException {
		Menu mainMenu = new Menu();
		while (!exit) {
			mainMenu.displayingLoginOptions();
			mainMenu.displayingAccountOptions();
			int task = mainMenu.getValidTaskInput();
			mainMenu.processingUserSelection(task);
		}
	}

	public BankAccount createAccount(String name) throws IOException {
		if (name == null || name.isEmpty()) {
			System.err.print("FATAL ERROR: name cannot be null !!");
		}

		account = new BankAccount(name);
		try {
			writeData(account);
		} catch (IOException e) {
			System.err.println("An error occurred while writing the account data: " + e.getMessage());
			return null;
		}
		return account;
	}

	private void writeData(BankAccount account) throws IOException {
		BigInteger hashCode = BigInteger.valueOf(getAccountHash(account)); // line number
		BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
		String name = account.getAccountName();
		String balance = String.valueOf(account.getBalance());
		String data = name + ";" + balance;

		fileData.writeData(recordNumber, data);

	}

	// Login
	public boolean readData(String accountName) throws IOException {
		BigInteger recordNumber = BigInteger.valueOf(accountName.hashCode());
		recordNumber = recordNumber.and(BigInteger.valueOf(Long.MAX_VALUE));

		try {
			String readData = fileData.readData(recordNumber);
			if (readData.isBlank()) {
				System.out.println("Account not found: " + accountName);
				return false;
			}
			String[] parts = readData.split(";");
			if (parts.length >= 2) {
				accountName = parts[0];
				Double balance = Double.parseDouble(parts[1]);
				account = createAccount(accountName);
				account.addBalance(account, balance);
				return true;
			} else {
				System.out.println("Invalid account data format");
				return false;
			}
		} catch (IOException e) {
			System.err.println("An error occurred while reading the account data: " + e.getMessage());
			return false;
		}
	}

	// Transferring to another account
	private BankAccount readSecondAccount(String accountName) throws IOException {
		BigInteger hashCode = BigInteger.valueOf(accountName.hashCode());
		BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
		String readData = fileData.readData(recordNumber);
		if (readData.isBlank()) {
			return null;
		}
		String[] parts = readData.split(";");
		accountName = parts[0];
		Double balance = Double.parseDouble(parts[1]);

		BankAccount secondAccount = createAccount(accountName);
		secondAccount.deposit(balance);
		return secondAccount;
	}

	public void displayingLoginOptions() throws IOException {
		System.out
				.println("If you have an account with us, please press one. Otherwise, press two:\n1) Log in 2) Sign up");
		try {
			int logininput = in.nextInt();

			if (logininput != 1 && logininput != 2) {
				System.err.println("pick 1 or  2");
				displayingLoginOptions();
			}

			if (logininput == 1) { // have an account and are signing in
				while (true) {
					System.out.println("Please enter username.");
					String tempUsername = in.next();
					if (!readData(tempUsername)) {
						System.err.println("Cannot retrieve account data.");
						continue;
					}
					break;
				}
			}

			else if (logininput == 2) {
				System.out.println("Welcome! Please create an account by providing a username.");
				while (true) {
					account = createAccount(in.next());
					if (account == null) {
						System.err.println("error making the account try again");
					} else {
						break;
					}
				}
			}

		} catch (InputMismatchException e) {
			System.err.println("Invalid input! Please enter an integer.");
			in.nextLine();
			displayingLoginOptions();
		}

	}

	public void displayingAccountOptions() {
		System.out.println("Enter the number of your desired action: "
				+ "\n 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Account Settings\n 5) Exit\n");
	}

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
				in.nextLine();
				displayingAccountOptions();
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

	public void processingUserSelection(int task) throws IOException {
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

	public void processingDeposit() throws IOException {
		double amount = validMoneyInput();
		account.deposit(amount);
		System.out.println("Your balance now is: $" + account.getBalance());
		writeData(account);

	}

	public void processingTransfer() throws IOException {
		System.out.println("Enter the name of the account to transfer to");
		String receivingAccountName = in.next();
		BankAccount receivingAccount = readSecondAccount(receivingAccountName);
		if (receivingAccount == null) {
			System.out.println("Invalid account name. Transfer failed.");
			return;
		}
		double amount = validMoneyInput();
		if (account.transfer(receivingAccount, amount)) {
			writeData(account);
			writeData(receivingAccount);
		}
	}

	public void processingWithdraw() throws IOException {
		System.out.println("Enter how much to withdraw in cash:");
		int withdrawAmount = in.nextInt();
		account.withdraw(withdrawAmount);
		System.out.println("Your balance now is: $" + account.getBalance());
		writeData(account);
	}

	public void accountSettings() throws IOException {
		System.out.println(
				"Enter the action you'd like: 1) View account information 2) Change username 3) Terminate account");
		int option = in.nextInt();
		switch (option) {
		case 1:
			getAccount();
			break;
		case 2:
			System.out.println("Enter new username: ");
			String newUsername = in.next();
			changeUsername(newUsername);
			break;
		case 3:
			terminateAccount(account.getAccountName());
			break;
		}
	}

	public BankAccount getAccount() {
		System.out.println(account.getAccountName());
		System.out.println(account.getBalance());
		return account;
	}

	public void terminateAccount(String accountName) throws IOException {

		BigInteger hashCode = BigInteger.valueOf(getAccountHash(account));

		BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));

		String data = "";

		fileData.writeData(recordNumber, data);

	}

	public void changeUsername(String newUsername) throws IOException {
		BigInteger hashCode = BigInteger.valueOf(getAccountHash(account));
		BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
		String balance = String.valueOf(account.getBalance());
		String data = newUsername + ";" + balance;

		fileData.writeData(recordNumber, data);
		
	}

}
