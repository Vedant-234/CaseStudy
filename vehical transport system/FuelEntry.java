

package com.vts;

public class FuelEntry {
    private String fuelId;
    private String fuelDate;
    private double liters;
    private double fuelCost;

    public FuelEntry(String fuelId, String fuelDate, double liters, double fuelCost) {
        this.fuelId = fuelId;
        this.fuelDate = fuelDate;
        this.liters = liters;
        this.fuelCost = fuelCost;
    }

    
    public String getFuelDate() {
        return fuelDate;
    }
    public double getFuelCost() {
        return fuelCost;
    }


    public void displayFuelEntry() {
        System.out.println("Fuel ID: " + fuelId);
        System.out.println("Date: " + fuelDate);
        System.out.println("Liters: " + liters);
        System.out.println("Fuel Cost: ₹" + fuelCost);
    }
}

