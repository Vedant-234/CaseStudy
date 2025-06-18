package com.vts;

public class FuelEntry {
	private String fuelId;
	private String fuelDate;
	private double liters;
	private double fuelCost;
	private String driverId;

	public FuelEntry(String fuelId, String fuelDate, double liters, double fuelCost,String driverId) {
		this.fuelId = fuelId;
		this.fuelDate = fuelDate;
		this.liters = liters;
		this.fuelCost = fuelCost;
		this.driverId = driverId;
	}



	public String getFuelDate() {
		return fuelDate;
	}
	public double getFuelCost() {
		return fuelCost;
	}


	
	
	public String getDriverId() {
        return this.driverId;
    }
	
	
	@Override
	public String toString() {
		return "FuelEntry [fuelId=" + fuelId + ", fuelDate=" + fuelDate + ", liters=" + liters + ", fuelCost="
				+ fuelCost + "]";
	}



	public void displayFuelEntry() {
		System.out.println("Fuel ID: " + fuelId);
		System.out.println("Date: " + fuelDate);
		System.out.println("Liters: " + liters);
		System.out.println("Fuel Cost: ₹" + fuelCost);
	}




}
