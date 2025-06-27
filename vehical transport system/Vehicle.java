package com.vts.model;

public class Vehicle {
    private String vehicleId;
    private String regNo;
    private String type;
    private int capacity;
    private String insuranceDetails;
    private String insuranceExpiry;
    private String pucDate;
    private String pucExpiry;

    public Vehicle() {}

    public Vehicle(String vehicleId, String regNo, String type, int capacity, 
                  String insuranceDetails, String insuranceExpiry, 
                  String pucDate, String pucExpiry) {
        this.vehicleId = vehicleId;
        this.regNo = regNo;
        this.type = type;
        this.capacity = capacity;
        this.insuranceDetails = insuranceDetails;
        this.insuranceExpiry = insuranceExpiry;
        this.pucDate = pucDate;
        this.pucExpiry = pucExpiry;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getInsuranceDetails() {
        return insuranceDetails;
    }
    
    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }
    
    public String getInsuranceExpiry() {
        return insuranceExpiry;
    }
    
    public void setInsuranceExpiry(String insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }
    
    public String getPucDate() {
        return pucDate;
    }
    
    public void setPucDate(String pucDate) {
        this.pucDate = pucDate;
    }
    
    public String getPucExpiry() {
        return pucExpiry;
    }
    
    public void setPucExpiry(String pucExpiry) {
        this.pucExpiry = pucExpiry;
    }

    @Override
    public String toString() {
        return "Vehicle [vehicleId=" + vehicleId + ", regNo=" + regNo + ", type=" + type 
               + ", capacity=" + capacity + ", insuranceDetails=" + insuranceDetails 
               + ", insuranceExpiry=" + insuranceExpiry + ", pucDate=" + pucDate 
               + ", pucExpiry=" + pucExpiry + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                vehicleId,
                regNo,
                type,
                String.valueOf(capacity),
                insuranceDetails,
                insuranceExpiry,
                pucDate,
                pucExpiry
        );
    }

    public static Vehicle fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 8) return null;
        return new Vehicle(
            parts[0], 
            parts[1], 
            parts[2], 
            Integer.parseInt(parts[3]),
            parts[4], 
            parts[5], 
            parts[6], 
            parts[7]
        );
    }
}
