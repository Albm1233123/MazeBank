package Backend;

import java.sql.*;

import Backend.BankEntities.Account;

public class accountDOA {
    
    // Create account
    public void createAccount(String accountNum, double balance, int userId) {
        String query = "INSERT INTO accounts (account_num, balance, user_id) VALUES (?, ?, ?)";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNum);
            statement.setDouble(2, balance);
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get account num and name
    public Account getAccountByAccountNum(String accountNum) {
        String query = "SELECT a.account_num, a.balance, a.user_id, u.username as owner_name " +
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
               
                String ownerName = resultSet.getString("owner_name");

                Account account = new Account(accountNumber, balance, userId);

                System.out.println("Account Owner: " + ownerName);

                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
