package com.ordercraft.ordercraft.dao;

import com.ordercraft.ordercraft.model.classes.User;
import com.ordercraft.ordercraft.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Method to add a new user to the database
    public void addUser(User user) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Users (username, password) VALUES (?, ?)")) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            System.out.println("User added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding user to the database.", e);
        }
    }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving users from the database.", e);
        }

        return users;
    }
    public User getUserByUsername(String username) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
        ) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));

                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user by username from the database.", e);
        }

        return null; // Return null if no user found with the provided username
    }



    public boolean updateUser(User user) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Users SET username=?, password=? WHERE userId=?")) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a user from the database
    public boolean deleteUser(int userId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Users WHERE userId=?")) {

            preparedStatement.setInt(1, userId);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
