package com.vts.ui.driver;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Accident;
import com.vts.model.Driver;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AccidentReportUI extends JFrame {
    private DataContext dataContext;
    private Driver driver;
    private JTextField accIdField, dateField, descField, costField;

    public AccidentReportUI(DataContext dataContext, Driver driver) {
        this.dataContext = dataContext;
        this.driver = driver;
        setTitle("Report Accident");
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Accident ID:"));
        accIdField = new JTextField();
        panel.add(accIdField);
        
        panel.add(new JLabel("Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);
        
        panel.add(new JLabel("Description:"));
        descField = new JTextField();
        panel.add(descField);
        
        panel.add(new JLabel("Cost:"));
        costField = new JTextField();
        panel.add(costField);
        
        JButton saveButton = new JButton("Report Accident");
        saveButton.addActionListener(this::saveAccident);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        add(panel);
    }

    private void saveAccident(ActionEvent e) {
        try {
            Accident accident = new Accident(
                accIdField.getText(),
                dateField.getText(),
                descField.getText(),
                Double.parseDouble(costField.getText()),
                driver.getDriverId()
            );
            
            dataContext.accidentRecords.add(accident);
            driver.addAccident(accident); // Add to driver's accident list
            JOptionPane.showMessageDialog(this, "Accident reported!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost value");
        }
    }
}