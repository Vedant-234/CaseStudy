package com.vts;

public class Maintenance {
	private String vehicleId;
	private String type;
	private String dueDate;
	private double cost;

	public Maintenance(String vehicleId, String type, String dueDate, double cost) {
		this.vehicleId = vehicleId;
		this.type = type;
		this.dueDate = dueDate;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public String getDueDate() { 
		return dueDate;
	}



	@Override
	public String toString() {
		return "Maintenance [vehicleId=" + vehicleId + ", type=" + type + ", dueDate=" + dueDate + ", cost=" + cost
				+ "]";
	}

	public void displayMaintenance() {
		System.out.println("Vehicle ID: " + vehicleId + ", Type: " + type + ", Due Date: " + dueDate + ", Cost: ₹" + cost);
	}
}
