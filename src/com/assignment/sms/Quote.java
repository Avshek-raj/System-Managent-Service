package com.assignment.sms;

public class Quote {
    private int customerId;
    private String customerName;
    private int id;

    private int serviceId;
    private String serviceName;
    private String status;
    private int assignedStaffId;
    private String assignedStaffName;

    public Quote(int id, int customerId, String serviceName, String status, String customerName, String assignedStaffName, int assignedStaffId, int serviceId) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.status = status;
        this.assignedStaffName = assignedStaffName;
        this.assignedStaffId = assignedStaffId;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }
    public int getCustomerId() {
        return customerId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAssignedStaffName() {
        return assignedStaffName;
    }

    public String getStatus() {
        return status;
    }

    public void setAssignedStaffId(int assignedStaffId) {
        this.assignedStaffId = assignedStaffId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignedStaffName(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service: " + serviceName + "\nCustomertId: " + customerId + "\nStatus: " + status + "\nAssigned staff: " + assignedStaffName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getAssignedStaffId() {
        return assignedStaffId;
    }

    public int getServiceId() {
        return serviceId;
    }
}
