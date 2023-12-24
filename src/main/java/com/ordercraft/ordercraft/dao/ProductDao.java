// ProductDao.java
package com.ordercraft.ordercraft.dao;

import com.ordercraft.ordercraft.model.classes.Product;
import com.ordercraft.ordercraft.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void addProduct(Product product) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Products (itemName, price, quantity) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStockQuantity());

            preparedStatement.executeUpdate();

            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product to the database.", e);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setItemId(resultSet.getInt("itemId"));
                product.setName(resultSet.getString("itemName"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStockQuantity(resultSet.getInt("quantity"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving products from the database.", e);
        }

        return products;
    }

    // Additional methods for updating or deleting products can be added as needed

    public boolean updateProduct(Product product) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Products SET itemName=?, price=?, quantity=? WHERE itemId=?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStockQuantity());
            preparedStatement.setInt(4, product.getItemId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Products WHERE itemId=?")) {

            preparedStatement.setInt(1, productId);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductById(int productId) {
        Product product = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products WHERE itemId=?")) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setItemId(resultSet.getInt("itemId"));
                product.setName(resultSet.getString("itemName"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStockQuantity(resultSet.getInt("quantity"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving product from the database.", e);
        }

        return product;
    }
}
