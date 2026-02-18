package com.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    public static Connection getConnection() throws SQLException {
        try {
            // 1. Check if we are in the Cloud (Render)
            String envUrl = System.getenv("DB_URL");
            
            if (envUrl != null) {
                // Uses the values you paste into Render's Environment Variables
                jdbcURL = envUrl;
                jdbcUsername = System.getenv("DB_USER");
                jdbcPassword = System.getenv("DB_PASSWORD");
            } else {
                // 2. Fallback for Local Testing (Your Laptop)
                // Use your local database name 'complaint_system' from your old file
                jdbcURL = "jdbc:mysql://localhost:3306/complaint_system?useSSL=false&allowPublicKeyRetrieval=true";
                jdbcUsername = "root";
                jdbcPassword = "Chandan@2005"; // Your local password from the screenshot
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
