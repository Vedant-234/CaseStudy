package com.vts.ui.admin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Maintenance;
import com.vts.ui.common.MaintenanceDetailUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MaintenanceManagementUI extends JFrame {
    private DataContext dataContext;
    private JTable maintenanceTable;
    private MaintenanceTableModel tableModel;

    public MaintenanceManagementUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Maintenance Management");
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        tableModel = new MaintenanceTableModel(dataContext.maintenanceRecords);
        maintenanceTable = new JTable(tableModel);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(maintenanceTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Maintenance");
        JButton editButton = new JButton("Edit Maintenance");
        JButton deleteButton = new JButton("Delete Maintenance");
        JButton backButton = new JButton("Back");
        
        addButton.addActionListener(this::addMaintenance);
        editButton.addActionListener(this::editMaintenance);
        deleteButton.addActionListener(this::deleteMaintenance);
        backButton.addActionListener(e -> dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void addMaintenance(ActionEvent e) {
        new MaintenanceDetailUI(dataContext, null).setVisible(true);
    }

    private void editMaintenance(ActionEvent e) {
        int row = maintenanceTable.getSelectedRow();
        if (row >= 0) {
            new MaintenanceDetailUI(dataContext, dataContext.maintenanceRecords.get(row)).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a maintenance record to edit");
        }
    }

    private void deleteMaintenance(ActionEvent e) {
        int row = maintenanceTable.getSelectedRow();
        if (row >= 0) {
            dataContext.maintenanceRecords.remove(row);
            tableModel.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a maintenance record to delete");
        }
    }
    
    class MaintenanceTableModel extends AbstractTableModel {
        private ArrayList<Maintenance> maintenanceRecords;
        private String[] columns = {"Vehicle ID", "Type", "Due Date", "Cost"};
        
        public MaintenanceTableModel(ArrayList<Maintenance> maintenanceRecords) {
            this.maintenanceRecords = maintenanceRecords;
        }
        
        @Override
        public int getRowCount() {
            return maintenanceRecords.size();
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
            Maintenance m = maintenanceRecords.get(row);
            switch (column) {
                case 0: return m.getVehicleId();
                case 1: return m.getType();
                case 2: return m.getDueDate();
                case 3: return "₹" + m.getCost();
                default: return null;
            }
        }
    }
}