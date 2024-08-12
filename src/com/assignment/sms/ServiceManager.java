package com.assignment.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static com.assignment.sms.DatabaseHelper.connect;

public class ServiceManager {
    private static List<Service> services = new ArrayList<>();

    public static List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM services";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                services.add(new Service(rs.getInt("ServiceId"), rs.getString("Name"), rs.getString("Description"),
                        rs.getDouble("EstimatedDuration"),rs.getDouble("COst")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public static Service addServiceToDatabase(String name, String description, double estimatedDuration, double cost) {
        Service service = null;

        final String DATABASE_URL = "jdbc:mysql://localhost/servicemanagementsystem";
        final String Username = "root";
        final String Password = "";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String insertUserSql = "INSERT INTO services (Name, Description, EstimatedDuration, Cost) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, estimatedDuration);
            preparedStatement.setDouble(4, cost);
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 ) {
                service = new Service(0, name, description,estimatedDuration, cost);
            }
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    public static void deleteService(int serviceId) {
        String query = String.format("DELETE FROM Services WHERE ServiceId='%d'", serviceId);
        DatabaseHelper.executeUpdate(query);
    }
}
