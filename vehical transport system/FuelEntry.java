package com.vts.model;

public class FuelEntry {
    private String fuelId;
    private String fuelDate;
    private double liters;
    private double fuelCost;
    private String driverId;

    public FuelEntry() {}
    
    public FuelEntry(String fuelId, String fuelDate, double liters, double fuelCost, String driverId) {
        this.fuelId = fuelId;
        this.fuelDate = fuelDate;
        this.liters = liters;
        this.fuelCost = fuelCost;
        this.driverId = driverId;
    }

    public String getFuelDate() {
        return fuelDate;
    }
    
    public void setFuelDate(String fuelDate) {
        this.fuelDate = fuelDate;
    }
    
    public double getFuelCost() {
        return fuelCost;
    }
    
    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }
    
    public String getFuelId() {
        return fuelId;
    }
    
    public void setFuelId(String fuelId) {
        this.fuelId = fuelId;
    }
    
    public double getLiters() {
        return liters;
    }
    
    public void setLiters(double liters) {
        this.liters = liters;
    }
    
    public String getDriverId() {
        return this.driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    @Override
    public String toString() {
        return "FuelEntry [fuelId=" + fuelId + ", fuelDate=" + fuelDate + ", liters=" + liters 
               + ", fuelCost=₹" + fuelCost + ", driverId=" + driverId + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                fuelId,
                fuelDate,
                String.valueOf(liters),
                String.valueOf(fuelCost),
                driverId
        );
    }

    public static FuelEntry fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 5) return null;
        return new FuelEntry(
            parts[0], 
            parts[1], 
            Double.parseDouble(parts[2]), 
            Double.parseDouble(parts[3]), 
            parts[4]
        );
    }
}