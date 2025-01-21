package Backend;

import java.sql.*;

public class transactionLogDOA {

    // Create transaction logs
    public void createTransaction(int accountId, String transactionType, double amount) {
        String query = "INSERT INTO transaction_logs (account_id, transaction_type, amount) VALUES (?, ?, ?)";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all logs for account
    public ResultSet getTransactionsByAccount(int accountId) {
        String query = "SELECT * FROM transaction_logs WHERE account_id = ? ORDER BY transaction_date DESC";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
