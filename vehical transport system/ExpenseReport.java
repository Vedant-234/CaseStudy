package com.vts.model;

public class ExpenseReport {
    private String reportId;
    private String month;
    private double totalFuelCost;
    private double totalMaintenanceCost;
    private double totalAccidentCost;

    public ExpenseReport(String reportId, String month, double totalFuelCost, 
                         double totalMaintenanceCost, double totalAccidentCost) {
        this.reportId = reportId;
        this.month = month;
        this.totalFuelCost = totalFuelCost;
        this.totalMaintenanceCost = totalMaintenanceCost;
        this.totalAccidentCost = totalAccidentCost;
    }

    // Getter methods
    public String getReportId() { return reportId; }
    public String getMonth() { return month; }
    public double getTotalFuelCost() { return totalFuelCost; }
    public double getTotalMaintenanceCost() { return totalMaintenanceCost; }
    public double getTotalAccidentCost() { return totalAccidentCost; }
    public double getGrandTotal() { 
        return totalFuelCost + totalMaintenanceCost + totalAccidentCost; 
    }
}