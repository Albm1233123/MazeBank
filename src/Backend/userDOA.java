package Backend;

import java.sql.*;

import Backend.BankEntities.User;

public class userDOA {

    // Create user
    public void createUser(String username, String passwordHash, String email) {
        String query = "INSERT INTO users (username, password_hash, email) VALUES (?, ?, ?)";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash); // temp not hashed as of now;
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get username
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = databaseConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String passwordHash = resultSet.getString("password_hash");
                String email = resultSet.getString("email");

                return new User(userId, username, passwordHash, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateUserCredentials(String username, String password) {
        String query = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection connection = databaseConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("password_hash");

                // temp password testing (add hash later)
                return password.equals(storedPasswordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}