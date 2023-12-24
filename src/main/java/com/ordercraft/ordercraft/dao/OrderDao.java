package com.ordercraft.ordercraft.dao;

import com.ordercraft.ordercraft.model.classes.Order;
import com.ordercraft.ordercraft.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    // Method to add a new order to the database
    public void addOrder(Order order) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Orders (clientId, orderDate, isCompleted) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, order.getClientId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setBoolean(3, order.isCompleted());

            preparedStatement.executeUpdate();

            System.out.println("Order added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding order to the database.", e);
        }
    }

    // Method to retrieve all orders from the database
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Orders");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setClientId(resultSet.getInt("clientId"));
                order.setOrderDate(resultSet.getDate("orderDate").toLocalDate());
                order.setCompleted(resultSet.getBoolean("isCompleted"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving orders from the database.", e);
        }

        return orders;
    }

    // Additional methods for updating or deleting orders can be added as needed

    public boolean updateOrder(Order order) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Orders SET clientId=?, orderDate=?, isCompleted=? WHERE orderId=?")) {

            preparedStatement.setInt(1, order.getClientId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setBoolean(3, order.isCompleted());
            preparedStatement.setInt(4, order.getOrderId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an order from the database
    public boolean deleteOrder(int orderId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Orders WHERE orderId=?")) {

            preparedStatement.setInt(1, orderId);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
