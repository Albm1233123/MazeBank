package Backend.BankEntities;

public class Account {

    private String accountNum;
    private double balance;
    private int userId; 
    private String accountName;

    public Account(String accountNum, double balance, int userId, String accountName) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.userId = userId;
        this.accountName = accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
