package com.assignment.sms;
public class ServiceRequest {
    private int customerId;
    private int serviceId;

    private String serviceName;
    private String date;
    private double cost;
    private String status;

    public ServiceRequest(int customerId, int serviceId, String date, double cost, String status, String serviceName) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.date = date;
        this.cost = cost;
        this.status = status;
        this.serviceName = serviceName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceName() {
        return serviceName;
    }
}


