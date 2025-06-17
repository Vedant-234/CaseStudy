package com.vts;

class TravelEntry {
    private String entryId;
    private String date;
    private double kilometers;

    public TravelEntry(String entryId, String date, double kilometers) {
        this.entryId = entryId;
        this.date = date;
        this.kilometers = kilometers;
    }
    
  
    public double getKilometers() {
        return kilometers;
    }



    public void displayTravelEntry() {
        System.out.println("Travel Entry ID: " + entryId);
        System.out.println("Date: " + date);
        System.out.println("Kilometers: " + kilometers);
    }
}


