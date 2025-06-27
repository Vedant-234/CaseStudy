package com.vts.ui.common;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Maintenance;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MaintenanceDetailUI extends JFrame {
    private DataContext dataContext;
    private Maintenance maintenance;
    private JTextField vehicleField, typeField, dateField, costField;

    public MaintenanceDetailUI(DataContext dataContext, Maintenance maintenance) {
        this.dataContext = dataContext;
        this.maintenance = maintenance;
        setTitle(maintenance == null ? "Add Maintenance" : "Edit Maintenance");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Vehicle ID:"));
        vehicleField = new JTextField();
        panel.add(vehicleField);
        
        panel.add(new JLabel("Type (PUC/Insurance):"));
        typeField = new JTextField();
        panel.add(typeField);
        
        panel.add(new JLabel("Due Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);
        
        panel.add(new JLabel("Cost:"));
        costField = new JTextField();
        panel.add(costField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveMaintenance);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        if (maintenance != null) {
            populateFields();
        }
        
        add(panel);
    }
    
    private void populateFields() {
        vehicleField.setText(maintenance.getVehicleId());
        typeField.setText(maintenance.getType());
        dateField.setText(maintenance.getDueDate());
        costField.setText(String.valueOf(maintenance.getCost()));
    }

    private void saveMaintenance(ActionEvent e) {
        try {
            Maintenance m = maintenance == null ? new Maintenance() : maintenance;
            m.setVehicleId(vehicleField.getText());
            m.setType(typeField.getText());
            m.setDueDate(dateField.getText());
            m.setCost(Double.parseDouble(costField.getText()));
            
            if (maintenance == null) {
                dataContext.maintenanceRecords.add(m);
            }
            
            JOptionPane.showMessageDialog(this, "Maintenance record saved!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost value");
        }
    }
}