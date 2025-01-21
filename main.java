import java.util.Scanner;

import BankEntities.Account;
import BankFunctions.*;

public class main {
    public static void main(String[] args) {
        Account account = new Account("212321", 0, "Alloysius");
        TransactionLog transactionLog = new TransactionLog();
        Deposit deposit = new Deposit(transactionLog);    
        Withdraw withdraw = new Withdraw(transactionLog);
        

        Scanner input = new Scanner(System.in);
        
        int choice = 0;

        while(true) {
            System.out.println("MazeBank");
            System.out.println("Welcome: " + account.getOwnerName());
            System.out.println("Current Balance: " + account.getBalance());
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Transaction Log");
            System.out.println("4) Exit");
            
            choice = input.nextInt();

            switch(choice) {
                case 1: {
                    System.out.println("Enter amount to Deposit");
                    double depositAmount = input.nextDouble();
                    deposit.deposit(account, depositAmount);
                    break;
                } 
                case 2: {
                    System.out.println("Enter amount to Withdraw");
                    double withdrawAmount = input.nextDouble();
                    withdraw.withdraw(account, withdrawAmount);
                    break;
                }
                case 3: {
                    System.out.println("Transaction logs");
                    transactionLog.printTransactionLogs(); 
                    break;
                }
                case 4: {
                    System.out.println("Exiting MazeBank");
                    input.close();
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }
    }
}
