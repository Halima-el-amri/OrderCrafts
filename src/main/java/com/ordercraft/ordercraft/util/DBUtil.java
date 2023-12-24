package com.ordercraft.ordercraft.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/OrderCraft";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("la connexion fait avec success");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no connection to datase" + e.getMessage());

        }
        return null;
    }



    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Méthode pour exécuter le script SQL
//    public static void executeSQLScript(String scriptPath) {
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement();
//             BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
//
//            String line;
//            StringBuilder script = new StringBuilder();
//
//            // Lecture du script SQL ligne par ligne
//            while ((line = reader.readLine()) != null) {
//                script.append(line).append("\n");
//            }
//
//            System.out.println("Executing SQL script:");
//            System.out.println(script.toString());
//
//            // Exécution du script SQL
//            statement.execute(script.toString());
//
//            System.out.println("SQL script executed successfully.");
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error executing SQL script.", e);
//        }
//    }

//DBUtil.executeSQLScript("path/to/your/schema.sql");



}






