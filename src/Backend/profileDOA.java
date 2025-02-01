package Backend;

import java.sql.*;

import Backend.BankEntities.Profile;

public class profileDOA {

    // Create user profile
    public void createProfile(int userId, String firstName, String lastName, Date dateOfBirth, String address, String phoneNumber) {
        String query = "INSERT INTO profiles (userID, firstName, lastName, dateOfBirth, address, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection connection = databaseConn.getConnection(); 
            PreparedStatement statement = connection.prepareStatement(query)) {
           statement.setInt(1, userId);
           statement.setString(2, firstName);
           statement.setString(3, lastName);
           statement.setDate(4, dateOfBirth);
           statement.setString(5, address);
           statement.setString(6, phoneNumber);
           statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update profile
    public void updateProfile(int userId, String firstName, String lastName, Date dateOfBirth, String address, String phoneNumber) {
        String query = "UPDATE profiles SET first_name = ?, last_name = ?, date_of_birth = ?, address = ?, phone_number = ? WHERE user_id = ?";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, dateOfBirth);
            statement.setString(4, address);
            statement.setString(5, phoneNumber);
            statement.setInt(6, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Get profile by userID
    public Profile getProfileByUserId(int userId) {
        String query = "SELECT * FROM profiles WHERE user_id = ?";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int profileId = resultSet.getInt("profile_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                return new Profile(profileId, userId, firstName, lastName, dateOfBirth, address, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}