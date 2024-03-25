package bankapp;

// a hash set doesnt allow duplicates and doesnt maintain insertion order
import java.util.HashSet;
import java.util.Set;

public class BankAccount {

	private double balance; // we can add balance to be part of the constructor
	private String accountName = "";
	private DepositHandler depositHandler;
	private static Set<String> allAccountNames = new HashSet<>();

	// -- Constructors are private because they shouldn't be called on from the
	// outside, call the createAccount method instead
	// Constructor with an account name
	private BankAccount(String name) {
		this.accountName = name;
		this.balance = 0;

		this.depositHandler = new DepositHandler();
//		 this.withdrawHandler = new WithdrawHandler();
//		 this.transferHandler = new TransferHandler();
		// we can do all the above or we can make a generic transaction handler class
		// that then deals with making these handlers

	}

	public String getAccountName() {
		return this.accountName;
	}

	public double getBalance() {
		return this.balance;
	}

	protected void addBalance(double amount) {
		this.balance += amount;
	}

	// Factory method for the constructor with the name
	// -- This and the other createAccount method would be the one to use when
	// making an account
	public static BankAccount createAccount(String name) {
		if (name == null || name.isEmpty()) {
			System.err.print("FATAL ERROR: name cannot be null !!");
		}

		if (!allAccountNames.contains(name)) {
			System.err.println("This account name is already taken");
			return null;
		}

		allAccountNames.add(name);
		return new BankAccount(name);
	}

	// TODO: add a createAccount method that also takes in an initial amount of cash
	// ?

	// TODO: TEMP: remove these: Factory method for the constructor without a name
	public static BankAccount createAccount() {
		// these temporary nameless accounts wont be added to the allAccountNames set
		return new BankAccount(null);
	}

	// TODO: a bankaccount shouldnt see other accounts, this should be
	// a banker focused feature
	public Set<String> getAllAccountNames() {
		return allAccountNames;
	}

	public void deposit(double amount){
	 	depositHandler.depositInAccount(this, amount);
	 }

}

