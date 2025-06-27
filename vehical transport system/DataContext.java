package com.vts.data;

import java.util.ArrayList;
import com.vts.model.*;

//Declares the class DataContext.
//This class acts like an in-memory database for your application.
//It holds all the main collections of data used throughout the system.

public class DataContext {
    public ArrayList<Vehicle> vehicles = new ArrayList<>();
    public ArrayList<Driver> drivers = new ArrayList<>();
    public ArrayList<TravelEntry> travelEntries = new ArrayList<>();
    public ArrayList<Maintenance> maintenanceRecords = new ArrayList<>();
    public ArrayList<Schedule> schedules = new ArrayList<>();
    public ArrayList<FuelEntry> fuelEntries = new ArrayList<>();
    public ArrayList<Accident> accidentRecords = new ArrayList<>();
    public ArrayList<ExpenseReport> expenseReports = new ArrayList<>(); // New list
    public Admin admin = new Admin("Disha", "9876543210", "admin123", "pass@123");
}
