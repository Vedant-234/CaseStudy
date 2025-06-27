package com.vts.data;

import java.io.*;
import java.util.List;
import java.util.function.Function;

import com.vts.model.Accident;
import com.vts.model.Driver;
import com.vts.model.FuelEntry;
import com.vts.model.Maintenance;
import com.vts.model.Schedule;
import com.vts.model.TravelEntry;
import com.vts.model.Vehicle;


//This class handles reading from and writing to files for all types of data in the system
public class DataStorage {
    private static final String DATA_DIR = "data/";
    
    public static void loadData(DataContext dataContext) {
        // Create data directory if it doesn't exist
        new File(DATA_DIR).mkdirs();
        
        loadFromFile(DATA_DIR + "vehicles.csv", dataContext.vehicles, Vehicle::fromCSV);
        loadFromFile(DATA_DIR + "drivers.csv", dataContext.drivers, Driver::fromCSV);
        loadFromFile(DATA_DIR + "schedules.csv", dataContext.schedules, Schedule::fromCSV);
        loadFromFile(DATA_DIR + "maintenance.csv", dataContext.maintenanceRecords, Maintenance::fromCSV);
        loadFromFile(DATA_DIR + "travel_entries.csv", dataContext.travelEntries, TravelEntry::fromCSV);
        loadFromFile(DATA_DIR + "fuel_entries.csv", dataContext.fuelEntries, FuelEntry::fromCSV);
        loadFromFile(DATA_DIR + "accidents.csv", dataContext.accidentRecords, Accident::fromCSV);
        
        // Rebuild driver-accident relationships
        for (Driver d : dataContext.drivers) {
            d.loadAccidents(dataContext.accidentRecords);
        }
    }
    
    public static void saveData(DataContext dataContext) {
        saveToFile(DATA_DIR + "vehicles.csv", dataContext.vehicles, Vehicle::toCSV);
        saveToFile(DATA_DIR + "drivers.csv", dataContext.drivers, Driver::toCSV);
        saveToFile(DATA_DIR + "schedules.csv", dataContext.schedules, Schedule::toCSV);
        saveToFile(DATA_DIR + "maintenance.csv", dataContext.maintenanceRecords, Maintenance::toCSV);
        saveToFile(DATA_DIR + "travel_entries.csv", dataContext.travelEntries, TravelEntry::toCSV);
        saveToFile(DATA_DIR + "fuel_entries.csv", dataContext.fuelEntries, FuelEntry::toCSV);
        saveToFile(DATA_DIR + "accidents.csv", dataContext.accidentRecords, Accident::toCSV);
    }
    
    private static <T> void loadFromFile(String filename, List<T> list, Function<String, T> parser) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                T obj = parser.apply(line);
                if (obj != null) {
                    list.add(obj);
                }
            }
            System.out.println("Loaded " + list.size() + " items from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading from " + filename + ": " + e.getMessage());
        }
    }
    
    private static <T> void saveToFile(String filename, List<T> list, Function<T, String> converter) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (T item : list) {
                pw.println(converter.apply(item));
            }
            System.out.println("Saved " + list.size() + " items to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }
}