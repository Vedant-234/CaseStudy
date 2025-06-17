package com.vts;

import java.util.ArrayList;

class Admin extends User {
    private String username;
    private String password;

    public Admin(String name, String contactInfo, String username, String password) {
        super(name, contactInfo);
        this.username = username;
        this.password = password;
    }

    public ExpenseReport generateMonthlyReport(
            String reportId,
            String month,
           
            ArrayList<FuelEntry> fuelEntries,
            ArrayList<Maintenance> maintenanceRecords,
            ArrayList<Accident> accidentRecords) {

        double totalFuel = 0, totalMaintenance = 0, totalAccident = 0;

        for (FuelEntry f : fuelEntries) {
//            if (f.getFuelDate().startsWith(month)) totalFuel += f.getFuelCost();
            
            String[] parts = f.getFuelDate().split("-");
            if (parts.length == 3) {
                String fuelMonthYear = parts[2] + "-" + parts[1];
                if (fuelMonthYear.equals(month)) {
                    totalFuel += f.getFuelCost();
                }
            }
            
            System.out.println(totalFuel);
        }

        for (Maintenance m : maintenanceRecords) {
//            if (m.getDueDate().startsWith(month)) totalMaintenance += m.getCost();
            
            String[] parts = m.getDueDate().split("-");
            if (parts.length == 3) {
                String maintMonthYear = parts[0] + "-" + parts[1];
                if (maintMonthYear.equals(month)) {
                    totalMaintenance += m.getCost();
                }
            }
            
            System.out.println(totalMaintenance);
        }

        for (Accident a : accidentRecords) {
//            if (a.getDate().startsWith(month)) totalAccident += a.getCost();
            
            String[] parts = a.getDate().split("-");
            if (parts.length == 3) {
                String accMonthYear = parts[2] + "-" + parts[1];
                if (accMonthYear.equals(month)) {
                    totalAccident += a.getCost();
                }
            }
            
            System.out.println(totalAccident);
        }

        return new ExpenseReport(reportId, month, totalFuel, totalMaintenance, totalAccident);
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pass) {
        return this.password.equals(pass);
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin Name: " + name);
        System.out.println("Contact: " + contactInfo);
    }
}
