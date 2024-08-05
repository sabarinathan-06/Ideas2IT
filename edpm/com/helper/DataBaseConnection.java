package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* This class is responsible for connecting to the database and creating a single instance for it.
*
* Author: Sabarinathan
*/
public class DataBaseConnection {
   
    private static DataBaseConnection instance;
    private Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/EmployeeManagement";
    private static final String userName = "postgres";
    private static final String password = "tiger";

    private DataBaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, userName, password);
            System.out.println("data base connected");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database Connection Creation Failed: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }

    /**
     * This method checks if the instance is null or the connection is closed,
then it creates a new instance; otherwise, it returns the existing instance.
     *
     * @return instance 
     *     - If it's null or connection is closed, returns new instance 
else returns existing instance.
     */
    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
}