package com.vts;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.data.DataStorage;
import com.vts.ui.common.LoginUI;

//Purpose: Initializes the application.


public class TransportSystemApp {
    public static void main(String[] args) {
    	//A DataContext object is created (stores all data in memory).
        DataContext dataContext = new DataContext();
        
        // Load data from files
        //DataStorage.loadData() reads CSV files (if they exist) and loads data into DataContext.
        DataStorage.loadData(dataContext);
        
        // Add shutdown hook to save data
        //A shutdown hook ensures data is saved when the app closes.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataStorage.saveData(dataContext);
        }));
        
        //The LoginUI window opens.
        SwingUtilities.invokeLater(() -> {
            new LoginUI(dataContext).setVisible(true);
        });
    }
}