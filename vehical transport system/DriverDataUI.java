package com.vts.ui.common;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Accident;
import com.vts.model.Driver;
import com.vts.model.FuelEntry;
import com.vts.model.TravelEntry;

import java.awt.*;
import java.util.ArrayList;

public class DriverDataUI extends JFrame {
    private DataContext dataContext;
    private Driver driver;

    public DriverDataUI(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle("Driver Data - " + driver.getDriverId());
        initUI();
    }

    private void initUI() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Travel Entries", createDriverTable("travel"));
        tabbedPane.addTab("Fuel Entries", createDriverTable("fuel"));
        tabbedPane.addTab("Accidents", createDriverTable("accident"));
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        
        add(panel);
    }

    private JScrollPane createDriverTable(String type) {
        JTable table = new JTable();
        switch (type) {
            case "travel":
                table.setModel(new TravelEntryTableModel(filterTravelEntries()));
                break;
            case "fuel":
                table.setModel(new FuelEntryTableModel(filterFuelEntries()));
                break;
            case "accident":
                table.setModel(new AccidentTableModel(filterAccidents()));
                break;
        }
        return new JScrollPane(table);
    }
    
    private ArrayList<TravelEntry> filterTravelEntries() {
        ArrayList<TravelEntry> result = new ArrayList<>();
        for (TravelEntry t : dataContext.travelEntries) {
            if (t.getDriverId().equals(driver.getDriverId())) {
                result.add(t);
            }
        }
        return result;
    }
    
    private ArrayList<FuelEntry> filterFuelEntries() {
        ArrayList<FuelEntry> result = new ArrayList<>();
        for (FuelEntry f : dataContext.fuelEntries) {
            if (f.getDriverId().equals(driver.getDriverId())) {
                result.add(f);
            }
        }
        return result;
    }
    
    private ArrayList<Accident> filterAccidents() {
        ArrayList<Accident> result = new ArrayList<>();
        for (Accident a : dataContext.accidentRecords) {
            if (a.getDriverId().equals(driver.getDriverId())) {
                result.add(a);
            }
        }
        return result;
    }
    
    // Table Models
    class TravelEntryTableModel extends AbstractTableModel {
        private ArrayList<TravelEntry> travelEntries;
        private String[] columns = {"ID", "Date", "Kilometers"};
        
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
                default: return null;
            }
        }
    }
    
    class FuelEntryTableModel extends AbstractTableModel {
        private ArrayList<FuelEntry> fuelEntries;
        private String[] columns = {"ID", "Date", "Liters", "Cost"};
        
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
                default: return null;
            }
        }
    }
    
    class AccidentTableModel extends AbstractTableModel {
        private ArrayList<Accident> accidentRecords;
        private String[] columns = {"ID", "Date", "Description", "Cost"};
        
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
                default: return null;
            }
        }
    }
}