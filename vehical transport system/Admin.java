package com.vts.model;

import java.util.ArrayList;

public class Admin extends User {
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

        String[] targetParts = month.split("-");
        if (targetParts.length != 2) {
            System.err.println("Invalid month format. Use YYYY-MM");
            return new ExpenseReport(reportId, month, 0, 0, 0);
        }
        

        for (FuelEntry f : fuelEntries) {
            String[] parts = f.getFuelDate().split("-");
            if (parts.length == 3) {
                String fuelMonthYear = parts[2] + "-" + parts[1];
                if (fuelMonthYear.equals(month)) {
                    totalFuel += f.getFuelCost();
                }
            }
        }

        for (Maintenance m : maintenanceRecords) {
            String[] parts = m.getDueDate().split("-");
            if (parts.length == 3) {
                String maintMonthYear = parts[2] + "-" + parts[1];
                if (maintMonthYear.equals(month)) {
                    totalMaintenance += m.getCost();
                }
            }
        }

        for (Accident a : accidentRecords) {
            String[] parts = a.getDate().split("-");
            if (parts.length == 3) {
                String accMonthYear = parts[2] + "-" + parts[1];
                if (accMonthYear.equals(month)) {
                    totalAccident += a.getCost();
                }
            }
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
    
    @Override
    public String toString() {
        return "Admin [name=" + name + ", contactInfo=" + contactInfo + ", username=" + username + "]";
    }
}