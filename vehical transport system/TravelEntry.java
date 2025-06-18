package com.vts;

class TravelEntry {
	private String entryId;
	private String date;
	private double kilometers;
	private String driverId;

	public TravelEntry(String entryId, String date, double kilometers, String driverId) {
		this.entryId = entryId;
		this.date = date;
		this.kilometers = kilometers;
		this.driverId = driverId;
	}

	///////////////////
	public double getKilometers() {
		return kilometers;
	}
	////////////////////////////



	public void displayTravelEntry() {
		System.out.println("Travel Entry ID: " + entryId);
		System.out.println("Date: " + date);
		System.out.println("Kilometers: " + kilometers);
	}
	
	
	public String getDriverId() {
        return this.driverId;
    }

	@Override
	public String toString() {
		return "TravelEntry [entryId=" + entryId + ", date=" + date + ", kilometers=" + kilometers + "]";
	}
	
	
	
	
	
	

}


