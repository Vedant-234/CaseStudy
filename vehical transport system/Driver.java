package com.vts.model;

import java.util.ArrayList;

public class Driver extends User {
    private String driverId;
    private String licenseNumber;
    private ArrayList<Accident> accidents = new ArrayList<>();

    public Driver() {}

    public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
        super(name, contactInfo);
        this.driverId = driverId;
        this.licenseNumber = licenseNumber;
    }

    public String getDriverId() {
        return driverId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getContactInfo() {
        return contactInfo;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void addAccident(Accident accident) {
        accidents.add(accident);
    }

    @Override
    public void displayInfo() {
        System.out.println("Driver Name: " + name);
        System.out.println("Contact Info: " + contactInfo);
        System.out.println("Driver ID: " + driverId);
        System.out.println("License No: " + licenseNumber);
    }

    @Override
    public String toString() {
        return "[Driver ID: " + driverId + ", Name: " + name + ", Contact: " + contactInfo + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                name,
                contactInfo,
                driverId,
                licenseNumber
        );
    }

    public static Driver fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) return null;
        return new Driver(
            parts[0], 
            parts[1], 
            parts[2], 
            parts[3]
        );
    }
    
    public void loadAccidents(ArrayList<Accident> accidents) {
        for (Accident a : accidents) {
            if (a.getDriverId().equals(driverId)) {
                this.accidents.add(a);
            }
        }
    }
}