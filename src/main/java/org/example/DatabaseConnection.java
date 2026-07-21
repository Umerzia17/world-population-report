package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public void closeConnection(Connection connection) throws SQLException
    {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }
}