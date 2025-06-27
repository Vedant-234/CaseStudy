package com.vts.ui.common;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Driver;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DriverDetailUI extends JFrame {
    private DataContext dataContext;
    private Driver driver;
    private JTextField idField, nameField, contactField, licenseField;

    public DriverDetailUI(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle(driver == null ? "Add Driver" : "Edit Driver");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Driver ID:"));
        idField = new JTextField();
        panel.add(idField);
        
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        panel.add(contactField);
        
        panel.add(new JLabel("License Number:"));
        licenseField = new JTextField();
        panel.add(licenseField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveDriver);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        if (driver != null) {
            populateFields();
        }
        
        add(panel);
    }
    
    private void populateFields() {
        idField.setText(driver.getDriverId());
        nameField.setText(driver.getName());
        contactField.setText(driver.getContactInfo());
        licenseField.setText(driver.getLicenseNumber());
    }

    private void saveDriver(ActionEvent e) {
        // Create new driver if needed
        if (driver == null) {
            driver = new Driver();
        }
        
        // Set values from fields
        driver.setDriverId(idField.getText());
        driver.setName(nameField.getText());
        driver.setContactInfo(contactField.getText());
        driver.setLicenseNumber(licenseField.getText());
        
        // Add to data context if new driver
        if (!dataContext.drivers.contains(driver)) {
            dataContext.drivers.add(driver);
        }
        
        JOptionPane.showMessageDialog(this, "Driver saved successfully!");
        dispose();
    }
}