package com.assignment.sms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.assignment.sms.DatabaseHelper.connect;

public class ReportManager {
    public static List<String> getRevenueReport() {
        List<String> report = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT services.name, SUM(services.cost) AS revenue " +
                    "FROM services " +
                    "JOIN quotes ON services.ServiceId = quotes.serviceId " +
                    "JOIN appointments ON quotes.id = appointments.quoteId " +
                    "GROUP BY services.name");
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    report.add(String.format("Service: %s, Revenue: %.2f", rs.getString("name"), rs.getDouble("revenue")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    public static List<String> getWorkloadDistribution() {
        List<String> report = new ArrayList<>();
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String query = "SELECT users.username, COUNT(appointments.id) AS workload " +
                    "FROM users " +
                    "JOIN appointments ON users.id = appointments.AssignedStaffId " +
                    "GROUP BY users.username";
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    report.add(String.format("Staff: %s, Workload: %d", rs.getString("username"), rs.getInt("workload")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return report;
    }

    public static List<String> getCustomerHistory(int customerId) {
        List<String> history = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT services.name, services.cost, appointments.date " +
                    "FROM services " +
                    "JOIN quotes ON services.serviceId = quotes.serviceId " +
                    "JOIN appointments ON quotes.id = appointments.quoteId " +
                    "WHERE quotes.customerId = %d", customerId);
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    history.add(String.format("Service: %s, Cost: %.2f, Date: %s", rs.getString("name"), rs.getDouble("cost"), rs.getString("date")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return history;
    }
}


