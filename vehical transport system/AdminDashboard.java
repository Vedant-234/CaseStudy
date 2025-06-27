package com.vts.ui.admin;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.ui.common.DataDisplayUI;
import com.vts.ui.common.LoginUI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {
    private DataContext dataContext;

    public AdminDashboard(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Admin Dashboard");
        setSize(600, 500); // Increased height for new button
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Added "Check PUC/Insurance Expiry" button
        String[] buttons = {
            "Register Vehicle", "Register Driver", "Plan Travel (Schedule)", 
            "Maintenance, PUC, Insurance", "Generate Monthly Expense Reports", 
            "Check upcoming PUC/Insurance Expiry", "Display All Data", "Exit"
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
            case "Register Vehicle":
                new VehicleManagementUI(dataContext).setVisible(true);
                break;
            case "Register Driver":
                new DriverManagementUI(dataContext).setVisible(true);
                break;
            case "Plan Travel (Schedule)":
                new ScheduleManagementUI(dataContext).setVisible(true);
                break;
            case "Maintenance, PUC, Insurance":
                new MaintenanceManagementUI(dataContext).setVisible(true);
                break;
            case "Generate Monthly Expense Reports":
                new ReportGenerationUI(dataContext).setVisible(true);
                break;
            case "Check upcoming PUC/Insurance Expiry":
                new PUCInsuranceCheckUI(dataContext).setVisible(true);
                break;
            case "Display All Data":
                new DataDisplayUI(dataContext).setVisible(true);
                break;
            case "Exit":
                // Go back to login page instead of exiting
                new LoginUI(dataContext).setVisible(true);
                dispose();
                break;
        }
    }
}