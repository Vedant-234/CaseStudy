
package com.vts;

public class Accident {
	private String accId;
	private String date;
	private String description;
	private double cost;
	private String driverId;

	public Accident(String accId, String date, String description, double cost,String driverId) {
		this.accId = accId;
		this.date = date;
		this.description = description;
		this.cost = cost;
		this.driverId = driverId;
	}

	public double getCost() {
		return cost;
	}

	public String getDate() { 
		return date;
	}
	
		
	public String getDriverId() {
        return this.driverId;
    }
	


	@Override
	public String toString() {
		return "Accident [accId=" + accId + ", date=" + date + ", description=" + description + ", cost=" + cost + "]";
	}

	public void displayAccident() {
		System.out.println("Accident ID: " + accId + ", Date: " + date + ", Description: " + description + ", Cost: ₹" + cost);
	}
	
	
}

