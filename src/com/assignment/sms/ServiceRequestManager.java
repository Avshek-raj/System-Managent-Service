package com.assignment.sms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import static com.assignment.sms.DatabaseHelper.connect;

public class ServiceRequestManager {
    private static List<ServiceRequest> serviceRequests = new ArrayList<>();

    public static List<ServiceRequest> getServiceRequestsByCustomerId(int customerId) {
        List<ServiceRequest> customerRequests = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT quotes.ServiceName, quotes.CustomerId, quotes.ServiceId, quotes.Status, services.Cost, appointments.Date " +
                    "FROM quotes " +
                    "JOIN services ON quotes.ServiceId = services.serviceId " +
                    "JOIN appointments ON quotes.id = appointments.quoteId " +
                    "WHERE quotes.customerId = '%d' ", customerId);
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    customerRequests.add(new ServiceRequest(rs.getInt("CustomerId"), rs.getInt("ServiceId"),
                            rs.getString("Date"), rs.getDouble("Cost"), rs.getString("Status"), rs.getString("ServiceName")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerRequests;
    }
}

