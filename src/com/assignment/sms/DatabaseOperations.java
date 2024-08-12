package com.assignment.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

class DatabaseOperations {
    public User addUserToDatabase(String username, String password, String address, String phone, String email) {
        User user = null;

        final String DATABASE_URL = "jdbc:mysql://localhost/servicemanagementsystem";
        final String Username = "root";
        final String Password = "";

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, Username, Password);
            Statement stmt = conn.createStatement();
            String insertUserSql = "INSERT INTO users (username, email, phone, address, password, type) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, "Customer");
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 ) {
                user = new User(0, username, password,"customer", address, phone, email);
                user.username = username;
                user.email = email;
                user.address = address;
                user.password = password;
                user.phone = phone;
                user.type = "customer";
            }
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}