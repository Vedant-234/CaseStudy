package com.vts;

class Driver extends User {
    private String driverId;
    private String licenseNumber;

    public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
        super(name, contactInfo);
        this.driverId = driverId;
        this.licenseNumber = licenseNumber;
    }

    public String getDriverId() {
        return driverId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Driver Name: " + name);
        System.out.println("Contact: " + contactInfo);
        System.out.println("Driver ID: " + driverId);
        System.out.println("License No: " + licenseNumber);
    }
}
