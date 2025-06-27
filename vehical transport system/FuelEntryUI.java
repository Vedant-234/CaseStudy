package com.vts.ui.driver;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Driver;
import com.vts.model.FuelEntry;

import java.awt.*;
import java.awt.event.ActionEvent;

public class FuelEntryUI extends JFrame {
    private DataContext dataContext;
    private Driver driver;
    private JTextField entryIdField, dateField, litersField, costField;

    public FuelEntryUI(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle("Add Fuel Entry");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Entry ID:"));
        entryIdField = new JTextField();
        panel.add(entryIdField);
        
        panel.add(new JLabel("Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);
        
        panel.add(new JLabel("Liters:"));
        litersField = new JTextField();
        panel.add(litersField);
        
        panel.add(new JLabel("Cost:"));
        costField = new JTextField();
        panel.add(costField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveEntry);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        add(panel);
    }

    private void saveEntry(ActionEvent e) {
        try {
            FuelEntry entry = new FuelEntry(
                entryIdField.getText(),
                dateField.getText(),
                Double.parseDouble(litersField.getText()),
                Double.parseDouble(costField.getText()),
                driver.getDriverId()
            );
            
            dataContext.fuelEntries.add(entry);
            JOptionPane.showMessageDialog(this, "Fuel entry saved!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid liters or cost value");
        }
    }
}