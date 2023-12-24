package com.ordercraft.ordercraft.dao;

import com.ordercraft.ordercraft.model.classes.Client;
import com.ordercraft.ordercraft.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    // Method to add a new client to the database
    public void addClient(Client client) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Clients (clientName, contactInfo) VALUES (?, ?)")) {

            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getContactInfo());

            preparedStatement.executeUpdate();

            System.out.println("Client added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding client to the database.", e);
        }
    }

    // Method to retrieve all clients from the database
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Clients");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setClientId(resultSet.getInt("clientId"));
                client.setClientName(resultSet.getString("clientName"));
                client.setContactInfo(resultSet.getString("contactInfo"));

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving clients from the database.", e);
        }

        return clients;
    }

    // Additional methods for updating or deleting clients can be added as needed

    public boolean updateClient(Client client) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Clients SET clientName=?, contactInfo=? WHERE clientId=?")) {

            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getContactInfo());
            preparedStatement.setInt(3, client.getClientId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a client from the database
    public boolean deleteClient(int clientId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Clients WHERE clientId=?")) {

            preparedStatement.setInt(1, clientId);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
