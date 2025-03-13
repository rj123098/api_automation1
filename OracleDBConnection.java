package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {

    public static void main(String[] args) {
        // Oracle JDBC Thin Driver URL format
        String jdbcUrl = "jdbc:oracle:thin:@//hostname:port/service_name";
        
        // Example:
        // String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orclpdb1";

        String username = "your_username";
        String password = "your_password";

        Connection connection = null;

        try {
            // Load Oracle JDBC Driver (optional in Java 6+, required for some versions)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("✅ Connected to Oracle Database!");
            } else {
                System.out.println("❌ Failed to connect to Oracle Database.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("✅ Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
