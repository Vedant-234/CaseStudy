package com.vts.ui.admin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Driver;
import com.vts.ui.common.DriverDetailUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DriverManagementUI extends JFrame {
    private DataContext dataContext;
    private JTable driverTable;
    private DriverTableModel tableModel;

    public DriverManagementUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Driver Management");
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        tableModel = new DriverTableModel(dataContext.drivers);
        driverTable = new JTable(tableModel);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(driverTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Driver");
        JButton editButton = new JButton("Edit Driver");
        JButton deleteButton = new JButton("Delete Driver");
        JButton backButton = new JButton("Back");
        
        addButton.addActionListener(this::addDriver);
        editButton.addActionListener(this::editDriver);
        deleteButton.addActionListener(this::deleteDriver);
        backButton.addActionListener(e -> dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    public void addDriver(ActionEvent e) {
        new DriverDetailUI(dataContext, null).setVisible(true);
    }

    private void editDriver(ActionEvent e) {
        int row = driverTable.getSelectedRow();
        if (row >= 0) {
            new DriverDetailUI(dataContext, dataContext.drivers.get(row)).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a driver to edit");
        }
    }

    private void deleteDriver(ActionEvent e) {
        int row = driverTable.getSelectedRow();
        if (row >= 0) {
            dataContext.drivers.remove(row);
            tableModel.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a driver to delete");
        }
    }
    
    class DriverTableModel extends AbstractTableModel {
        private ArrayList<Driver> drivers;
        private String[] columns = {"ID", "Name", "Contact", "License"};
        
        public DriverTableModel(ArrayList<Driver> drivers) {
            this.drivers = drivers;
        }
        
        @Override
        public int getRowCount() {
            return drivers.size();
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
            Driver d = drivers.get(row);
            switch (column) {
                case 0: return d.getDriverId();
                case 1: return d.getName();
                case 2: return d.getContactInfo();
                case 3: return d.getLicenseNumber();
                default: return null;
            }
        }
    }
}