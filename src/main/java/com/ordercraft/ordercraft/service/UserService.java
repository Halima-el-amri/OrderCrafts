package com.ordercraft.ordercraft.service;

import com.ordercraft.ordercraft.dao.UserDao;
import com.ordercraft.ordercraft.model.classes.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // Method to add a new user
    public void addUser(User user) {
        userDao.addUser(user);
    }

    // Method to retrieve all users
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // Method to update a user
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    // Method to delete a user
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }



    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    // Login method
    public boolean loginUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            return true;
        } else {
            return false;
        }
    }
    public boolean isUsernameTaken(String username) {
        List<User> existingUsers = userDao.getAllUsers();

        for (User user : existingUsers) {
            if (user.getUsername().equals(username)) {
                return true; // Username is already taken
            }
        }

        return false; // Username is available
    }

    // Add additional business logic as needed for user management and login
}
