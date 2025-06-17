//package com.vts;
//
//public class Accident {
//    private String accidentId;
//    private String date;
//    private String description;
//    private String vehicleId;
//
//    public Accident(String accidentId, String date, String description, String vehicleId) {
//        this.accidentId = accidentId;
//        this.date = date;
//        this.description = description;
//        this.vehicleId = vehicleId;
//    }
//
//    public void displayAccident() {
//        System.out.println("Accident ID: " + accidentId);
//        System.out.println("Date: " + date);
//        System.out.println("Description: " + description);
//        System.out.println("Vehicle ID: " + vehicleId);
//    }
//}


// Updated Accident.java
//package com.vts;
//
//public class Accident {
//    private String accidentId;
//    private String date;
//    private String description;
//    private String vehicleId;
//
//    public Accident(String accidentId, String date, String description, String vehicleId) {
//        this.accidentId = accidentId;
//        this.date = date;
//        this.description = description;
//        this.vehicleId = vehicleId;
//    }
//
//    public void displayAccident() {
//        System.out.println("Accident ID: " + accidentId);
//        System.out.println("Date: " + date);
//        System.out.println("Description: " + description);
//        System.out.println("Vehicle ID: " + vehicleId);
//    }
//}


//package com.vts;
//
//public class Accident {
//    private String accId;
//    private String date;
//    private String description;
//
//    public Accident(String accId, String date, String description) {
//        this.accId = accId;
//        this.date = date;
//        this.description = description;
//    }
//
//    public void displayAccident() {
//        System.out.println("Accident ID: " + accId + ", Date: " + date + ", Description: " + description);
//    }
//}

package com.vts;

public class Accident {
    private String accId;
    private String date;
    private String description;
    private double cost;

    public Accident(String accId, String date, String description, double cost) {
        this.accId = accId;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getDate() { 
        return date;
    }

    public void displayAccident() {
        System.out.println("Accident ID: " + accId + ", Date: " + date + ", Description: " + description + ", Cost: ₹" + cost);
    }
}

