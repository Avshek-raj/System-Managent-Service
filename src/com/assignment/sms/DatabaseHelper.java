package com.assignment.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/servicemanagementsystem";
    static final String Username = "root";
    static final String Password = "";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DATABASE_URL, Username, Password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "type TEXT NOT NULL)";

        String serviceTable = "CREATE TABLE IF NOT EXISTS services (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT, " +
                "duration INTEGER, " +
                "cost REAL)";

        String quoteTable = "CREATE TABLE IF NOT EXISTS quotes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_id INTEGER, " +
                "service_id INTEGER, " +
                "status TEXT, " +
                "FOREIGN KEY(customer_id) REFERENCES users(id), " +
                "FOREIGN KEY(service_id) REFERENCES services(id))";

        String appointmentTable = "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quote_id INTEGER, " +
                "date TEXT, " +
                "time TEXT, " +
                "staff_id INTEGER, " +
                "FOREIGN KEY(quote_id) REFERENCES quotes(id), " +
                "FOREIGN KEY(staff_id) REFERENCES users(id))";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(serviceTable);
            stmt.execute(quoteTable);
            stmt.execute(appointmentTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            return stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void executeUpdate(String update) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

