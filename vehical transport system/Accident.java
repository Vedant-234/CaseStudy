package com.vts.model;

public class Accident {
    private String accId;
    private String date;
    private String description;
    private double cost;
    private String driverId;

    public Accident() {}
    
    public Accident(String accId, String date, String description, double cost, String driverId) {
        this.accId = accId;
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.driverId = driverId;
    }

    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDate() { 
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getAccId() {
        return accId;
    }
    
    public void setAccId(String accId) {
        this.accId = accId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDriverId() {
        return this.driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Accident [accId=" + accId + ", date=" + date + ", description=" + description 
               + ", cost=₹" + cost + ", driverId=" + driverId + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                accId,
                date,
                description,
                String.valueOf(cost),
                driverId
        );
    }

    public static Accident fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 5) return null;
        return new Accident(
            parts[0], 
            parts[1], 
            parts[2], 
            Double.parseDouble(parts[3]), 
            parts[4]
        );
    }
}