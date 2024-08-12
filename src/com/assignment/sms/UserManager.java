package com.assignment.sms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.assignment.sms.DatabaseHelper.connect;

public class UserManager {
    public static User registerUser(String username, String password, String type) {
        String query = String.format("INSERT INTO users (username, password, type) VALUES ('%s', '%s', '%s')", username, password, type);
        DatabaseHelper.executeUpdate(query);
        return null;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("type"),
                rs.getString("address"),rs.getString("phone"),  rs.getString("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void deleteUser(int id) {
        String query = String.format("DELETE FROM users WHERE username='%d'", id);
        DatabaseHelper.executeUpdate(query);
    }
}

