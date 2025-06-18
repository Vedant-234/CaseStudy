package com.vts;

class Vehicle {
    private String vehicleId;
    private String regNo;
    private String type;
    private int capacity;
    private String insuranceDetails;
    private String insuranceExpiry;
    private String pucDate;
    private String pucExpiry;

    public Vehicle(String vehicleId, String regNo, String type, int capacity, String insuranceDetails, String insuranceExpiry, String pucDate, String pucExpiry) {
        this.vehicleId = vehicleId;
        this.regNo = regNo;
        this.type = type;
        this.capacity = capacity;
        this.insuranceDetails = insuranceDetails;
        this.insuranceExpiry = insuranceExpiry;
        this.pucDate = pucDate;
        this.pucExpiry = pucExpiry;
    }
    
    ///////////////
    public String getVehicleId() {
        return vehicleId;
    }
/////////////////////////////

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Registration No: " + regNo);
        System.out.println("Type: " + type);
        System.out.println("Capacity: " + capacity);
        System.out.println("Insurance Details: " + insuranceDetails);
        System.out.println("Insurance Expiry: " + insuranceExpiry);
        System.out.println("PUC Date: " + pucDate);
        System.out.println("PUC Expiry: " + pucExpiry);
    }

    ///////////////////////
	@Override
	public String toString() {
		return "Vehicle : vehicleId = " + vehicleId + 
				", regNo = " + regNo + 
				", type = " + type + 
				", capacity = " + capacity+
				", insuranceDetails = " + insuranceDetails + 
				", insuranceExpiry = " + insuranceExpiry + 
				", pucDate = "+ pucDate + 
				", pucExpiry = " + pucExpiry + "";
	}
	 ///////////////////////
    
   
    
}



