package com.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/complaint_system";
    private static final String USER = "root";
    // *** IMPORTANT: REPLACE WITH YOUR MYSQL PASSWORD ***
    private static final String PASSWORD = "Chandan@2005"; 

    public static Connection getConnection() throws SQLException {
        try {
            // This line loads the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // This will happen if you forgot to add the mysql-connector jar file
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}