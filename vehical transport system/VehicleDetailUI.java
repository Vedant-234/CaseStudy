package com.vts.ui.common;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VehicleDetailUI extends JFrame {
    private DataContext dataContext;
    private Vehicle vehicle;
    private JTextField idField, regNoField, typeField, capacityField;
    private JTextField insuranceField, insuranceExpiryField, pucDateField, pucExpiryField;

    public VehicleDetailUI(DataContext dataContext, Vehicle vehicle) {
        this.dataContext = dataContext;
        this.vehicle = vehicle;
        setTitle(vehicle == null ? "Add Vehicle" : "Edit Vehicle");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Vehicle ID:"));
        idField = new JTextField();
        panel.add(idField);
        
        panel.add(new JLabel("Registration No:"));
        regNoField = new JTextField();
        panel.add(regNoField);
        
        panel.add(new JLabel("Type:"));
        typeField = new JTextField();
        panel.add(typeField);
        
        panel.add(new JLabel("Capacity:"));
        capacityField = new JTextField();
        panel.add(capacityField);
        
        panel.add(new JLabel("Insurance Details:"));
        insuranceField = new JTextField();
        panel.add(insuranceField);
        
        panel.add(new JLabel("Insurance Expiry:"));
        insuranceExpiryField = new JTextField();
        panel.add(insuranceExpiryField);
        
        panel.add(new JLabel("PUC Date:"));
        pucDateField = new JTextField();
        panel.add(pucDateField);
        
        panel.add(new JLabel("PUC Expiry:"));
        pucExpiryField = new JTextField();
        panel.add(pucExpiryField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveVehicle);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        if (vehicle != null) {
            populateFields();
        }
        
        add(panel);
    }
    
    private void populateFields() {
        idField.setText(vehicle.getVehicleId());
        regNoField.setText(vehicle.getRegNo());
        typeField.setText(vehicle.getType());
        capacityField.setText(String.valueOf(vehicle.getCapacity()));
        insuranceField.setText(vehicle.getInsuranceDetails());
        insuranceExpiryField.setText(vehicle.getInsuranceExpiry());
        pucDateField.setText(vehicle.getPucDate());
        pucExpiryField.setText(vehicle.getPucExpiry());
    }

    private void saveVehicle(ActionEvent e) {
        try {
            Vehicle v = vehicle == null ? new Vehicle() : vehicle;
            v.setVehicleId(idField.getText());
            v.setRegNo(regNoField.getText());
            v.setType(typeField.getText());
            v.setCapacity(Integer.parseInt(capacityField.getText()));
            v.setInsuranceDetails(insuranceField.getText());
            v.setInsuranceExpiry(insuranceExpiryField.getText());
            v.setPucDate(pucDateField.getText());
            v.setPucExpiry(pucExpiryField.getText());
            
            if (vehicle == null) {
                dataContext.vehicles.add(v);
            }
            
            JOptionPane.showMessageDialog(this, "Vehicle saved successfully!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid capacity value");
        }
    }
}