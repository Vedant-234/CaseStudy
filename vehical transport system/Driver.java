////package com.vts;
////
//////import java.util.ArrayList;
//////import java.util.Scanner;
////
////class Driver extends User {
////    private String driverId;
////    private String licenseNumber;
////
////    public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
////        super(name, contactInfo);
////        this.driverId = driverId;
////        this.licenseNumber = licenseNumber;
////    }
////
////    public String getDriverId() {
////        return driverId;
////    }
////    
////    ////////////////
////    public String getName() {
////        return name;
////    }
//////////////////////////////
////
////    @Override
////    public void displayInfo() {
////        System.out.println("Driver Name: " + name);
////        System.out.println("Contact: " + contactInfo);
////       // System.out.println("Driver ID: " + driverId);
////    }
////    
////	@Override
////	public String toString() {
////		return "Driver : driverID = " + driverId + 
////				", Driver Name = " +  name + 
////				", contactInfo = " + contactInfo + 
////				", licenseNumber = " + licenseNumber + "";
////	}
//	
//	
//package com.vts;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Driver extends User {
//    private String driverId;
//    private String licenseNumber;
//    private ArrayList<Accident> accidents;
//
//    public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
//        super(name, contactInfo);
//        this.driverId = driverId;
//        this.licenseNumber = licenseNumber;
//        this.accidents = new ArrayList<>();
//    }
//
//    public String getDriverId() {
//        return driverId;
//    }
//
////    public void addAccidentEntry(Scanner sc) {
////        System.out.print("Enter Accident ID: ");
////        String accId = sc.nextLine();
////        System.out.print("Enter Date (dd-mm-yyyy): ");
////        String date = sc.nextLine();
////        System.out.print("Enter Description: ");
////        String desc = sc.nextLine();
////
////        Accident a = new Accident(accId, date, desc);
////        accidents.add(a);
////        System.out.println("Accident Reported Successfully!");
////        a.displayAccident();
////    }
//    
//    
//
//    public void displayAccidentHistory() {
//        System.out.println("Accident History for Driver: " + driverId);
//        for (Accident a : accidents) {
//            a.displayAccident();
//        }
//    }
//
//    @Override
//    public void displayInfo() {
//        System.out.println("Driver Name: " + name);
//        System.out.println("Contact Info: " + contactInfo);
//        System.out.println("Driver ID: " + driverId);
//        System.out.println("License No: " + licenseNumber);
//    }
//
//    @Override
//    public String toString() {
//        return "[Driver ID: " + driverId + ", Name: " + name + ", Contact: " + contactInfo + "]";
//    }
//}
//
//



package com.vts;

import java.util.ArrayList;
import java.util.Scanner;

class Driver extends User {
    private String driverId;
    private String licenseNumber;
    private ArrayList<Accident> accidents = new ArrayList<>();

    public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
        super(name, contactInfo);
        this.driverId = driverId;
        this.licenseNumber = licenseNumber;
    }

    public String getDriverId() {
        return driverId;
    }

    public void addAccidentEntry(Scanner sc, ArrayList<Accident> globalAccidentList) {
        System.out.print("Enter Accident ID: ");
        String accId = sc.nextLine();
        System.out.print("Enter Date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Cost: ");
        double cost = sc.nextDouble(); sc.nextLine();

        Accident a = new Accident(accId, date, desc, cost);
        accidents.add(a);
        globalAccidentList.add(a);
        System.out.println("Accident Reported Successfully!");
        a.displayAccident();
    }

    public void displayAccidentHistory() {
        System.out.println("Accident History for Driver: " + driverId);
        for (Accident a : accidents) {
            a.displayAccident();
        }
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
}
