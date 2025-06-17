package com.vts;

public class ExpenseReport {
    private String reportId;
    private String month;
    private double totalFuelCost;
    private double totalMaintenanceCost;
    private double totalAccidentCost;

    public ExpenseReport(String reportId, String month, double totalFuelCost, double totalMaintenanceCost, double totalAccidentCost) {
        this.reportId = reportId;
        this.month = month;
        this.totalFuelCost = totalFuelCost;
        this.totalMaintenanceCost = totalMaintenanceCost;
        this.totalAccidentCost = totalAccidentCost;
    }

    public void displayReport() {
        System.out.println("\n======= Monthly Expense Report =======");
        System.out.println("Report ID: " + reportId);
        System.out.println("Month: " + month);
        System.out.println("Total Fuel Cost: ₹" + totalFuelCost);
        System.out.println("Total Maintenance Cost: ₹" + totalMaintenanceCost);
        System.out.println("Total Accident Cost: ₹" + totalAccidentCost);
        System.out.println("Grand Total: ₹" + (totalFuelCost + totalMaintenanceCost + totalAccidentCost));
    }
}
