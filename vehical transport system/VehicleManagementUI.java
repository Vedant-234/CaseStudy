package com.vts.ui.admin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Vehicle;
import com.vts.ui.common.VehicleDetailUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VehicleManagementUI extends JFrame {
    private DataContext dataContext;
    private JTable vehicleTable;
    private VehicleTableModel tableModel;

    public VehicleManagementUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Vehicle Management");
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        tableModel = new VehicleTableModel(dataContext.vehicles);
        vehicleTable = new JTable(tableModel);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Vehicle");
        JButton editButton = new JButton("Edit Vehicle");
        JButton deleteButton = new JButton("Delete Vehicle");
        JButton backButton = new JButton("Back");
        
        addButton.addActionListener(this::addVehicle);
        editButton.addActionListener(this::editVehicle);
        deleteButton.addActionListener(this::deleteVehicle);
        backButton.addActionListener(e -> dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void addVehicle(ActionEvent e) {
        new VehicleDetailUI(dataContext, null).setVisible(true);
    }

    private void editVehicle(ActionEvent e) {
        int row = vehicleTable.getSelectedRow();
        if (row >= 0) {
            new VehicleDetailUI(dataContext, dataContext.vehicles.get(row)).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to edit");
        }
    }

    private void deleteVehicle(ActionEvent e) {
        int row = vehicleTable.getSelectedRow();
        if (row >= 0) {
            dataContext.vehicles.remove(row);
            tableModel.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to delete");
        }
    }
    
    class VehicleTableModel extends AbstractTableModel {
        private ArrayList<Vehicle> vehicles;
        private String[] columns = {"ID", "Reg No", "Type", "Capacity", "Insurance", "PUC"};
        
        public VehicleTableModel(ArrayList<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }
        
        @Override
        public int getRowCount() {
            return vehicles.size();
        }
        
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
        
        @Override
        public Object getValueAt(int row, int column) {
            Vehicle v = vehicles.get(row);
            switch (column) {
                case 0: return v.getVehicleId();
                case 1: return v.getRegNo();
                case 2: return v.getType();
                case 3: return v.getCapacity();
                case 4: return v.getInsuranceDetails() + " (Exp: " + v.getInsuranceExpiry() + ")";
                case 5: return v.getPucDate() + " (Exp: " + v.getPucExpiry() + ")";
                default: return null;
            }
        }
    }
}