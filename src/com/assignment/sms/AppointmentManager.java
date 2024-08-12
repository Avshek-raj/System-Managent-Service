package com.assignment.sms;

import java.sql.*;
import java.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.assignment.sms.DatabaseHelper.connect;

public class AppointmentManager {
    private static List<Appointment> appointments = new ArrayList<>();

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointments";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                appointments.add(new Appointment(rs.getInt("Id"), rs.getInt("CustomerId"), rs.getString("ServiceName"),
                        rs.getString("Date"), rs.getString("Time"), rs.getInt("AssignedStaffId"), rs.getString("AssignedStaffMember"), rs.getString("CustomerName")));
            }
        } catch (Exception e) {;
            e.printStackTrace();
        }
        return appointments;
    }

    public static void addAppointmentInDatabase(int customerId, int serviceId, String serviceName, int assignedStaffId, String assignedStaffMember, String customerName) {

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String insertAppointmentSql = "INSERT INTO Appointments (ServiceId, ServiceName, CustomerId, CustomerName, AssignedStaffId, AssignedStaffMember) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertAppointmentSql);
            preparedStatement.setInt(1, serviceId);
            preparedStatement.setString(2, serviceName);
            preparedStatement.setInt(3, customerId);
            preparedStatement.setString(4, customerName);
            preparedStatement.setInt(5, assignedStaffId);
            preparedStatement.setString(6, assignedStaffMember);
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 ) {
            }
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean scheduleAppointment(int id, String date, String time) {
        Boolean success = false;

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            String query = String.format("UPDATE Appointments set Date = '" + date + "', Time = '" + time + "'WHERE id = '%d'", id);
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 ) {
                success =true;
            }
            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}



