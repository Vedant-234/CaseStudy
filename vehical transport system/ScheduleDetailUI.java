package com.vts.ui.common;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.Schedule;
import com.vts.model.Driver;
import com.vts.model.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ScheduleDetailUI extends JFrame {
    private DataContext dataContext;
    private Schedule schedule;
    private JTextField idField, dateField, fromField, toField, vehicleField, driverField;

    public ScheduleDetailUI(DataContext dataContext, Schedule schedule) {
        this.dataContext = dataContext;
        this.schedule = schedule;
        setTitle(schedule == null ? "Add Schedule" : "Edit Schedule");
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Schedule ID:"));
        idField = new JTextField();
        panel.add(idField);
        
        panel.add(new JLabel("Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);
        
        panel.add(new JLabel("From Location:"));
        fromField = new JTextField();
        panel.add(fromField);
        
        panel.add(new JLabel("To Location:"));
        toField = new JTextField();
        panel.add(toField);
        
        panel.add(new JLabel("Vehicle ID:"));
        vehicleField = new JTextField();
        panel.add(vehicleField);
        
        panel.add(new JLabel("Driver ID:"));
        driverField = new JTextField();
        panel.add(driverField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveSchedule);
        panel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        if (schedule != null) {
            populateFields();
        }
        
        add(panel);
    }
    
    private void populateFields() {
        idField.setText(schedule.getScheduleId());
        dateField.setText(schedule.getDate());
        fromField.setText(schedule.getFrom());
        toField.setText(schedule.getTo());
        vehicleField.setText(schedule.getVehicleId());
        driverField.setText(schedule.getDriverId());
    }

    private void saveSchedule(ActionEvent e) {
        Schedule s = schedule == null ? new Schedule() : schedule;
        s.setScheduleId(idField.getText());
        s.setDate(dateField.getText());
        s.setFrom(fromField.getText());
        s.setTo(toField.getText());
        s.setVehicleId(vehicleField.getText());
        s.setDriverId(driverField.getText());
        
     // Validate vehicle exists
        boolean vehicleExists = false;
        for (Vehicle v : dataContext.vehicles) {
            if (v.getVehicleId().equals(s.getVehicleId())) {
                vehicleExists = true;
                break;
            }
        }
        if (!vehicleExists) {
            JOptionPane.showMessageDialog(this, 
                "Vehicle ID not registered!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
     // Validate driver exists
        boolean driverExists = false;
        for (Driver d : dataContext.drivers) {
            if (d.getDriverId().equals(s.getDriverId())) {
                driverExists = true;
                break;
            }
        }
        if (!driverExists) {
            JOptionPane.showMessageDialog(this, 
                "Driver ID not registered!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // New validation: Check for existing schedule with same date, vehicle, and driver
        for (Schedule existing : dataContext.schedules) {
            // Skip current schedule during edit
            if (schedule != null && existing == schedule) {
                continue;
            }
            
            // Check for conflict
            if (existing.getDate().equals(s.getDate()) && 
                existing.getVehicleId().equals(s.getVehicleId()) || 
                existing.getDriverId().equals(s.getDriverId())) {
                JOptionPane.showMessageDialog(this, 
                    "Error: This vehicle or driver is already scheduled for " + s.getDate() + ".",
                    "Conflict Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        if (schedule == null) {
            dataContext.schedules.add(s);
        }
        
        JOptionPane.showMessageDialog(this, "Schedule saved successfully!");
        dispose();
    }
}