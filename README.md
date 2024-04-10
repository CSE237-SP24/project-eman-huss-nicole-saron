# base-project
Iteration 2:
- this iteration --> We worked on account persistance which involved using a file as a database of sorts. We then used a hashmap to access and maniupulate the accounts. We improved our account manangement by adding options to terminate account, editing their username, as well as logining in and out of accounts. We also updated our "menu screen" to better match the changes. 
- next iteration --> we want to do more test cases. We want to make it so that readData() and writeData() retrieve/write one account at a time from the file to improve security and efficiency. We're unsure of how the File.java class was used in the files lecture, and how to utilize it inout code. We think it'd be where we'd check if a file exists, but not sure what else it'd do. We would also like to add account types such as savings vs checkings.  
- things that currently don't work: terminate account test case, login and logout aren't in Menu yet, ...
- our script is the same as before!
- 

Iteration1:
To start this project, we created a branch for each task (withdraw, account manager, deposit, transfer) as to prevent merge conflicts. After preliminary coding, we combined all code into the development branch. bankapp holds 5 files (BankAccount, Menu, DepositHandler, WithdrawHandler, and TransferHandler), and tests holds the respective tests. The BankAccount file manages the accounts by using a HashMap to store created accounts, and the file holds methods for deposit, withdraw, and transfer. The Menu file is the interface to run commands (deposit, withdraw, etc). The handler files for deposit, withdraw, and transfer execute the command only if there are no errors. We accounted for errors such as negative dollar amounts and excessive dollar amounts. In the test files, we check the functionality of the bankapp files. The BankAccountTests check if actions affect the proper account name. MenuTests checks if amounts properly update. The tests for the handlers properly checks if the accounted for errors do not impact the balance.

Currently, you can use the provided script to cd into the src file and run the commands. Running Menu provides the following user stories: deposit, withdraw, transfer, account, and exit. Based on our tests, we need to fix transfer so it can take in accounts. In the next iteration, we intend to continue working on the TransferHandler, MenuTests, and BankAccountTests. We could also maybe add a user story that logs in and out of accounts.
