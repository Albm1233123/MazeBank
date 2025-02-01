package Backend.BankFunctions;

import Backend.BankEntities.Account;

public class Deposit {

    TransactionLog transactionLog;

    public Deposit(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

    public void deposit(Account account, double amount) {

        if(amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Amount Deposited:" + amount);
            transactionLog.addLog("Cash Deposited: " + amount);
        } else {
            System.out.println("Invalid: Balance is empty");
        }
    }
}
