package com.vts.ui.driver;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.data.DataStorage;
import com.vts.model.Driver;
import com.vts.ui.common.DriverDataUI;
import com.vts.ui.common.LoginUI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DriverDashboard extends JFrame {
    private DataContext dataContext;
    private Driver driver;

    public DriverDashboard(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle("Driver Dashboard - " + driver.getDriverId());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] buttons = {
            "Add Travel Entry", "Add Fuel Entry", "Report Accident",
            "View My Data", "Exit"
        };
        
        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.addActionListener(this::menuAction);
            panel.add(button);
        }
        
        add(panel);
    }

    private void menuAction(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        switch (command) {
            case "Add Travel Entry":
                new TravelEntryUI(dataContext, driver).setVisible(true);
                break;
            case "Add Fuel Entry":
                new FuelEntryUI(dataContext, driver).setVisible(true);
                break;
            case "Report Accident":
                new AccidentReportUI(dataContext, driver).setVisible(true);
                break;
            case "View My Data":
                new DriverDataUI(dataContext, driver).setVisible(true);
                break;
            case "Exit":
                exitToLogin();
                break;
        }
    }

    private void exitToLogin() {
        // Save data before exiting
        DataStorage.saveData(dataContext);
        
        // Return to login page
        new LoginUI(dataContext).setVisible(true);
        dispose();
    }
    
    // Override window closing event to save data
    @Override
    public void dispose() {
        DataStorage.saveData(dataContext);
        super.dispose();
    }
}