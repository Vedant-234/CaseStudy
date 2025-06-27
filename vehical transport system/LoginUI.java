package com.vts.ui.common;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.data.DataStorage;
import com.vts.model.Driver;
import com.vts.ui.admin.AdminDashboard;
import com.vts.ui.driver.DriverDashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginUI extends JFrame {
    private JComboBox<String> userTypeCombo;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DataContext dataContext;

    public LoginUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Vehicle Transport System - Login");
        setSize(400, 350); // Increased height for exit button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Add window listener to save data on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DataStorage.saveData(dataContext);
            }
        });
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        formPanel.add(new JLabel("Login as:"));
        userTypeCombo = new JComboBox<>(new String[]{"Admin", "Driver"});
        formPanel.add(userTypeCombo);
        
        formPanel.add(new JLabel("Username/Driver ID:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        
        // Add empty labels for spacing
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::loginAction);
        buttonPanel.add(loginButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            DataStorage.saveData(dataContext);
            System.exit(0);
        });
        buttonPanel.add(exitButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeCombo.getSelectedItem();
        
        if ("Admin".equals(userType)) {
            if (dataContext.admin.getUsername().equals(username) && 
                dataContext.admin.checkPassword(password)) {
                new AdminDashboard(dataContext).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Admin Credentials!");
            }
        } else {
            Driver driver = findDriverById(username);
            if (driver != null) {
                new DriverDashboard(dataContext, driver).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Driver not found!");
            }
        }
    }

    private Driver findDriverById(String driverId) {
        for (Driver d : dataContext.drivers) {
            if (d.getDriverId().equals(driverId)) {
                return d;
            }
        }
        return null;
    }
}