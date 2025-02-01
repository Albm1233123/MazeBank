package Backend.BankEntities;

public class Account {

    private String accountNum;
    private double balance;
    private int userId; 

    public Account(String accountNum, double balance, int userId) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
