package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {


    Connection connection;

    public DatabaseManager() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        try {
            // Establish the connection to the database
            System.out.println("trying to connect");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ensao", "root", "");

            System.out.println("connection made successfully");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                // Close the connection to the database
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
