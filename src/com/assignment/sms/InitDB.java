package com.assignment.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InitDB {
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String DB_NAME = "ServiceManagementSystem";
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    InitDB(){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Check if the database exists
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + DB_NAME + "'");
            if (!rs.next()) {
                // Database does not exist, create it
                stmt.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("Database created: " + DB_NAME);
            } else {
                System.out.println("Database already exists: " + DB_NAME);
            }

            // Connect to the database
            try (Connection dbConn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
                 Statement dbStmt = dbConn.createStatement()) {

                // Check and create tables if they don't exist
                String[] tableQueries = {
                        "CREATE TABLE IF NOT EXISTS Users (" +
                                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                                "Username VARCHAR(50) ," +
                                "Email VARCHAR(50) ," +
                                "Address VARCHAR(20) ," +
                                "Phone VARCHAR(20) ," +
                                "Type VARCHAR(20) ," +
                                "password VARCHAR(20) " +
                                ")",

                        "CREATE TABLE IF NOT EXISTS Services (" +
                                "ServiceId INT AUTO_INCREMENT PRIMARY KEY," +
                                "Name VARCHAR(50) ," +
                                "Description VARCHAR(500)," +
                                "estimatedDuration DOUBLE(10,2)," +
                                "cost DOUBLE(10, 2)" +
                                ")",

                        "CREATE TABLE IF NOT EXISTS Quotes (" +
                                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                                "CustomerId INT," +
                                "CustomerName VARCHAR(50) ," +
                                "ServiceId INT," +
                                "ServiceName VARCHAR(50) ," +
                                "Status VARCHAR(20) ," +
                                "AssignedStaffId INT," +
                                "AssignedStaffName VARCHAR(50)" +
                                ")",

                        "CREATE TABLE IF NOT EXISTS Appointments (" +
                                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                                "ServiceName VARCHAR(50) ," +
                                "CustomerId INT," +
                                "CustomerName VARCHAR(50) ," +
                                "AssignedStaffId INT," +
                                "AssignedStaffMember VARCHAR(50) ," +
                                "QuoteId INT," +
                                "Date VARCHAR(50) ," +
                                "Time VARCHAR(50) " +
                                ")"
                };

                for (String query : tableQueries) {
                    dbStmt.executeUpdate(query);
                }

                System.out.println("Tables checked/created successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

