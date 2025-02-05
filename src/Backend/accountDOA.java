package Backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Backend.BankEntities.Account;

public class accountDOA {
    
    // Create account
    public void createAccount(String accountNum, double balance, int userId, String accountName) {
        String query = "INSERT INTO accounts (account_num, balance, user_id, account_name) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setString(1, accountNum);
             statement.setDouble(2, balance);
             statement.setInt(3, userId);
             statement.setString(4, accountName);
    
             System.out.println("Executing query: " + statement);  
    
             int rowsAffected = statement.executeUpdate();
             System.out.println("Rows affected: " + rowsAffected);  
    
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
    

    // Get account num and name
    public Account getAccountByAccountNum(String accountNum) {
        String query = "SELECT a.account_num, a.balance, a.user_id, a.account_name, u.username as owner_name " +
                       "FROM accounts a " +
                       "JOIN users u ON a.user_id = u.user_id " +
                       "WHERE a.account_num = ?";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNum);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String accountNumber = resultSet.getString("account_num");
                double balance = resultSet.getDouble("balance");
                int userId = resultSet.getInt("user_id");
                String accountName = resultSet.getString("account_name");
                String ownerName = resultSet.getString("owner_name");
    
                Account account = new Account(accountNumber, balance, userId, accountName);
    
                System.out.println("Account Owner: " + ownerName);
                System.out.println("Account Name: " + accountName);
    
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    

     // Get all accounts for a user
     public List<Account> getAccountsByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT account_num, balance, account_name FROM accounts WHERE user_id = ?";
        System.out.println("Executing query: " + query + " for userId: " + userId);
        
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String accountNum = resultSet.getString("account_num");
                double balance = resultSet.getDouble("balance");
                String accountName = resultSet.getString("account_name");
                
                System.out.println("Retrieved Account: " + accountNum + ", " + accountName);
                accounts.add(new Account(accountNum, balance, userId, accountName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
    public void deleteAccount(String accountNum) {
        String sql = "DELETE FROM accounts WHERE account_num = ?";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountNum);
            int rowsDeleted = stmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
