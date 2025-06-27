package com.vts.ui.admin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Schedule;
import com.vts.ui.common.ScheduleDetailUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ScheduleManagementUI extends JFrame {
    private DataContext dataContext;
    private JTable scheduleTable;
    private ScheduleTableModel tableModel;

    public ScheduleManagementUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Schedule Management");
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        tableModel = new ScheduleTableModel(dataContext.schedules);
        scheduleTable = new JTable(tableModel);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(scheduleTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Schedule");
        JButton editButton = new JButton("Edit Schedule");
        JButton deleteButton = new JButton("Delete Schedule");
        JButton backButton = new JButton("Back");
        
        addButton.addActionListener(this::addSchedule);
        editButton.addActionListener(this::editSchedule);
        deleteButton.addActionListener(this::deleteSchedule);
        backButton.addActionListener(e -> dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void addSchedule(ActionEvent e) {
        new ScheduleDetailUI(dataContext, null).setVisible(true);
    }

    private void editSchedule(ActionEvent e) {
        int row = scheduleTable.getSelectedRow();
        if (row >= 0) {
            new ScheduleDetailUI(dataContext, dataContext.schedules.get(row)).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a schedule to edit");
        }
    }

    private void deleteSchedule(ActionEvent e) {
        int row = scheduleTable.getSelectedRow();
        if (row >= 0) {
            dataContext.schedules.remove(row);
            tableModel.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a schedule to delete");
        }
    }
    
    class ScheduleTableModel extends AbstractTableModel {
        private ArrayList<Schedule> schedules;
        private String[] columns = {"ID", "Date", "From", "To", "Vehicle ID", "Driver ID"};
        
        public ScheduleTableModel(ArrayList<Schedule> schedules) {
            this.schedules = schedules;
        }
        
        @Override
        public int getRowCount() {
            return schedules.size();
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
            Schedule s = schedules.get(row);
            switch (column) {
                case 0: return s.getScheduleId();
                case 1: return s.getDate();
                case 2: return s.getFrom();
                case 3: return s.getTo();
                case 4: return s.getVehicleId();
                case 5: return s.getDriverId();
                default: return null;
            }
        }
    }
}