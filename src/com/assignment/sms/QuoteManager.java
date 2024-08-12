package com.assignment.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.assignment.sms.DatabaseHelper.connect;

public class QuoteManager {
    private static List<Quote> quotes = new ArrayList<>();
    public static User user;

    public static List<Quote> getAllQuotes() {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT * FROM Quotes";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                quotes.add(new Quote(rs.getInt("Id"), rs.getInt("CustomerId"), rs.getString("ServiceName"),
                        rs.getString("Status"), rs.getString("CustomerName"), rs.getString("AssignedStaffName"), rs.getInt("AssignedStaffId"), rs.getInt("ServiceId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quotes;
    }

    public static Quote addQuoteToDatabase(int customerId, int serviceId, String serviceName, String status, String customerName) {
        Quote quote = null;

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String insertUserSql = "INSERT INTO Quotes (CustomerId, ServiceId, ServiceName, Status, CustomerName) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(1, serviceId);
            preparedStatement.setString(2, serviceName);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, customerName);
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 ) {
                quote = new Quote(0, customerId, serviceName,status, customerName, null, 0, serviceId);
            }
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return quote;
    }

    public static Quote assignStaff(int staffId, String assignedStaffName, int quoteId) {
        Quote quote = null;

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String insertUserSql = String.format("UPDATE Quotes set AssignedStaffId = '%d', AssignedStaffName = '%s' where Id = '%d'", staffId, assignedStaffName, quoteId);
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql);
            int addedRows = preparedStatement.executeUpdate();
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return quote;
    }

    public static void deleteQuote(int id) {
        String query = String.format("DELETE FROM Quotes WHERE Id ='%d'", id);
        DatabaseHelper.executeUpdate(query);
    }

    public static Quote changeQuoteStatus(String state, int quoteId) {
        Quote quote = null;

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String insertUserSql = String.format("UPDATE Quotes set Status = '%s' where Id = '%d'", state, quoteId);
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql);
            int addedRows = preparedStatement.executeUpdate();
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return quote;
    }
}

