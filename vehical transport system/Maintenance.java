package com.vts.model;

public class Maintenance {
    private String vehicleId;
    private String type;
    private String dueDate;
    private double cost;

    public Maintenance() {}
    
    public Maintenance(String vehicleId, String type, String dueDate, double cost) {
        this.vehicleId = vehicleId;
        this.type = type;
        this.dueDate = dueDate;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDueDate() { 
        return dueDate;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Maintenance [vehicleId=" + vehicleId + ", type=" + type + ", dueDate=" + dueDate 
               + ", cost=₹" + cost + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                vehicleId,
                type,
                dueDate,
                String.valueOf(cost)
        );
    }

    public static Maintenance fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) return null;
        return new Maintenance(
            parts[0], 
            parts[1], 
            parts[2], 
            Double.parseDouble(parts[3])
        );
    }
}