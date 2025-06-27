package com.vts.ui.driver;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Driver;
import com.vts.model.TravelEntry;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TravelEntryUI extends JFrame {
    private DataContext dataContext;
    private Driver driver;
    private JTextField entryIdField, dateField, kmsField;

    public TravelEntryUI(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle("Add Travel Entry");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Entry ID:"));
        entryIdField = new JTextField();
        panel.add(entryIdField);
        
        panel.add(new JLabel("Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);
        
        panel.add(new JLabel("Kilometers:"));
        kmsField = new JTextField();
        panel.add(kmsField);
        
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
            TravelEntry entry = new TravelEntry(
                entryIdField.getText(),
                dateField.getText(),
                Double.parseDouble(kmsField.getText()),
                driver.getDriverId()
            );
            
            dataContext.travelEntries.add(entry);
            JOptionPane.showMessageDialog(this, "Travel entry saved!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid kilometers value");
        }
    }
}