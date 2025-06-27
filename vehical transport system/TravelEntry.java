package com.vts.model;

public class TravelEntry {
    private String entryId;
    private String date;
    private double kilometers;
    private String driverId;

    public TravelEntry() {}
    
    public TravelEntry(String entryId, String date, double kilometers, String driverId) {
        this.entryId = entryId;
        this.date = date;
        this.kilometers = kilometers;
        this.driverId = driverId;
    }

    public double getKilometers() {
        return kilometers;
    }
    
    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }
    
    public String getEntryId() {
        return entryId;
    }
    
    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDriverId() {
        return this.driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "TravelEntry [entryId=" + entryId + ", date=" + date + ", kilometers=" + kilometers 
               + ", driverId=" + driverId + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                entryId,
                date,
                String.valueOf(kilometers),
                driverId
        );
    }

    public static TravelEntry fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) return null;
        return new TravelEntry(
            parts[0], 
            parts[1], 
            Double.parseDouble(parts[2]), 
            parts[3]
        );
    }
}