package com.assignment.sms;


import java.util.Date;

public class Appointment {
    private int id;

    private int customerId;
    private String customerName;
    private String serviceName;
    private String date;
    private String time;
    private int assignedStaffId;
    private String assignedStaffMember;

    public Appointment(int id, int customerId, String serviceName, String date, String time, int assignedStaffId, String assignedStaffMember, String customerName) {
        this.id = id;
        this.customerId = customerId;
        this.serviceName = serviceName;
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.assignedStaffId = assignedStaffId;
        this.assignedStaffMember = assignedStaffMember;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAssignedStaffId() {
        return assignedStaffId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAssignedStaffMember() {
        return assignedStaffMember;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAssignedStaffMember(String assignedStaffMember) {
        this.assignedStaffMember = assignedStaffMember;
    }

}
