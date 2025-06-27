package com.vts.ui.common;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.*;

import java.awt.*;
import java.util.ArrayList;

public class DataDisplayUI extends JFrame {
    private DataContext dataContext;

    public DataDisplayUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Data Display");
        initUI();
    }

    private void initUI() {
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Vehicles", createTable("vehicles"));
        tabbedPane.addTab("Drivers", createTable("drivers"));
        tabbedPane.addTab("Schedules", createTable("schedules"));
        tabbedPane.addTab("Maintenance", createTable("maintenance"));
        tabbedPane.addTab("Travel Entries", createTable("travel"));
        tabbedPane.addTab("Fuel Entries", createTable("fuel"));
        tabbedPane.addTab("Accidents", createTable("accident"));
        tabbedPane.addTab("Expense Reports", createTable("expense"));
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        
        add(panel);
    }

    private JScrollPane createTable(String type) {
        JTable table = new JTable();
        switch (type) {
            case "vehicles":
                table.setModel(new VehicleTableModel(dataContext.vehicles));
                break;
            case "drivers":
                table.setModel(new DriverTableModel(dataContext.drivers));
                break;
            case "schedules":
                table.setModel(new ScheduleTableModel(dataContext.schedules));
                break;
            case "maintenance":
                table.setModel(new MaintenanceTableModel(dataContext.maintenanceRecords));
                break;
            case "travel":
                table.setModel(new TravelEntryTableModel(dataContext.travelEntries));
                break;
            case "fuel":
                table.setModel(new FuelEntryTableModel(dataContext.fuelEntries));
                break;
            case "accident":
                table.setModel(new AccidentTableModel(dataContext.accidentRecords));
                break;
            case "expense":
                table.setModel(new ExpenseReportTableModel(dataContext.expenseReports));
                break;
        }
        return new JScrollPane(table);
    }
    
    // Table Models
    class VehicleTableModel extends AbstractTableModel {
        private ArrayList<Vehicle> vehicles;
        private String[] columns = {"ID", "Reg No", "Type", "Capacity", "Insurance", "PUC"};
        
        public VehicleTableModel(ArrayList<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }
        
        @Override public int getRowCount() { return vehicles.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            Vehicle v = vehicles.get(row);
            switch (col) {
                case 0: return v.getVehicleId();
                case 1: return v.getRegNo();
                case 2: return v.getType();
                case 3: return v.getCapacity();
                case 4: return v.getInsuranceDetails();
                case 5: return v.getPucDate();
                default: return null;
            }
        }
    }
    
    class DriverTableModel extends AbstractTableModel {
        private ArrayList<Driver> drivers;
        private String[] columns = {"ID", "Name", "Contact", "License"};
        
        public DriverTableModel(ArrayList<Driver> drivers) {
            this.drivers = drivers;
        }
        
        @Override public int getRowCount() { return drivers.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            Driver d = drivers.get(row);
            switch (col) {
                case 0: return d.getDriverId();
                case 1: return d.getName();
                case 2: return d.getContactInfo();
                case 3: return d.getLicenseNumber();
                default: return null;
            }
        }
    }
    
    class ScheduleTableModel extends AbstractTableModel {
        private ArrayList<Schedule> schedules;
        private String[] columns = {"ID", "Date", "From", "To", "Vehicle ID", "Driver ID"};
        
        public ScheduleTableModel(ArrayList<Schedule> schedules) {
            this.schedules = schedules;
        }
        
        @Override public int getRowCount() { return schedules.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            Schedule s = schedules.get(row);
            switch (col) {
                case 0: return s.getScheduleId();
                case 1: return s.getDate();
                case 2: return s.getFrom();
                case 3: return s.getTo();
                case 4: return s.getVehicleId();
                case 5: return s.getDriverId();
                default: return null;
            }
        }
    }
    
    class MaintenanceTableModel extends AbstractTableModel {
        private ArrayList<Maintenance> maintenanceRecords;
        private String[] columns = {"Vehicle ID", "Type", "Due Date", "Cost"};
        
        public MaintenanceTableModel(ArrayList<Maintenance> maintenanceRecords) {
            this.maintenanceRecords = maintenanceRecords;
        }
        
        @Override public int getRowCount() { return maintenanceRecords.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            Maintenance m = maintenanceRecords.get(row);
            switch (col) {
                case 0: return m.getVehicleId();
                case 1: return m.getType();
                case 2: return m.getDueDate();
                case 3: return "₹" + m.getCost();
                default: return null;
            }
        }
    }
    
    class TravelEntryTableModel extends AbstractTableModel {
        private ArrayList<TravelEntry> travelEntries;
        private String[] columns = {"ID", "Date", "Kilometers", "Driver ID"};
        
        public TravelEntryTableModel(ArrayList<TravelEntry> travelEntries) {
            this.travelEntries = travelEntries;
        }
        
        @Override public int getRowCount() { return travelEntries.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            TravelEntry t = travelEntries.get(row);
            switch (col) {
                case 0: return t.getEntryId();
                case 1: return t.getDate();
                case 2: return t.getKilometers();
                case 3: return t.getDriverId();
                default: return null;
            }
        }
    }
    
    class FuelEntryTableModel extends AbstractTableModel {
        private ArrayList<FuelEntry> fuelEntries;
        private String[] columns = {"ID", "Date", "Liters", "Cost", "Driver ID"};
        
        public FuelEntryTableModel(ArrayList<FuelEntry> fuelEntries) {
            this.fuelEntries = fuelEntries;
        }
        
        @Override public int getRowCount() { return fuelEntries.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            FuelEntry f = fuelEntries.get(row);
            switch (col) {
                case 0: return f.getFuelId();
                case 1: return f.getFuelDate();
                case 2: return f.getLiters();
                case 3: return "₹" + f.getFuelCost();
                case 4: return f.getDriverId();
                default: return null;
            }
        }
    }
    
    class AccidentTableModel extends AbstractTableModel {
        private ArrayList<Accident> accidentRecords;
        private String[] columns = {"ID", "Date", "Description", "Cost", "Driver ID"};
        
        public AccidentTableModel(ArrayList<Accident> accidentRecords) {
            this.accidentRecords = accidentRecords;
        }
        
        @Override public int getRowCount() { return accidentRecords.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            Accident a = accidentRecords.get(row);
            switch (col) {
                case 0: return a.getAccId();
                case 1: return a.getDate();
                case 2: return a.getDescription();
                case 3: return "₹" + a.getCost();
                case 4: return a.getDriverId();
                default: return null;
            }
        }
    }

    class ExpenseReportTableModel extends AbstractTableModel {
        private ArrayList<ExpenseReport> expenseReports;
        private String[] columns = {"Report ID", "Month", "Fuel Cost", "Maintenance Cost", "Accident Cost", "Grand Total"};

        public ExpenseReportTableModel(ArrayList<ExpenseReport> reports) {
            this.expenseReports = reports;
        }

        @Override public int getRowCount() { return expenseReports.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int col) { return columns[col]; }
        
        @Override
        public Object getValueAt(int row, int col) {
            ExpenseReport r = expenseReports.get(row);
            switch (col) {
                case 0: return r.getReportId();
                case 1: return r.getMonth();
                case 2: return "₹" + String.format("%.2f", r.getTotalFuelCost());
                case 3: return "₹" + String.format("%.2f", r.getTotalMaintenanceCost());
                case 4: return "₹" + String.format("%.2f", r.getTotalAccidentCost());
                case 5: return "₹" + String.format("%.2f", r.getGrandTotal());
                default: return null;
            }
        }
    }
            
}