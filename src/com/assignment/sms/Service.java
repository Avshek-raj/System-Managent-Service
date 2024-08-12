package com.assignment.sms;

public class Service {
    private int id;
    private String name;
    private String description;
    private double estimatedDuration;
    private double cost;

    public Service(int id,String name, String description, double estimatedDuration, double cost) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getEstimatedDuration() {
        return estimatedDuration;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nEstimated Duration in hr: " + estimatedDuration + "\nCost: " + cost;
    }
}


