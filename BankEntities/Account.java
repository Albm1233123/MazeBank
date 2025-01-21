package BankEntities;

public class Account {

    private String accountNum;
    private double balance;
    private String ownerName;

    public Account(String accountNum, double balance, String ownerName) {

        this.accountNum = accountNum;
        this.balance = balance;
        this.ownerName = ownerName;
    } 

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}