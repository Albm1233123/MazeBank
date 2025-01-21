package BankFunctions;

import BankEntities.Account;

public class Withdraw {

    
    TransactionLog transactionLog;

    public Withdraw(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }
    
    public void withdraw(Account account, double amount) {

        if(account.getBalance() > 0) {
            account.setBalance(account.getBalance() - amount); 
            System.out.println("Amount Withdrawn: " + amount);
            transactionLog.addLog("Cash Withdrawn: " + amount);
        } else {
            System.out.println("Invalid: Balance is empty");
        }
    }
}
