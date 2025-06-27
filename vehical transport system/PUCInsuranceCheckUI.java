package com.vts.ui.admin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.vts.data.DataContext;
import com.vts.model.Vehicle;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class PUCInsuranceCheckUI extends JFrame {
    private JTable expiryTable;

    public PUCInsuranceCheckUI(DataContext dataContext) {
        setTitle("PUC/Insurance Expiry Check");
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        // Create table model with expiry status
        ExpiryTableModel model = new ExpiryTableModel(dataContext.vehicles);
        expiryTable = new JTable(model);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(expiryTable), BorderLayout.CENTER);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        
        panel.add(closeButton, BorderLayout.SOUTH);
        add(panel);
    }
    
    class ExpiryTableModel extends AbstractTableModel {
        private ArrayList<Vehicle> vehicles;
        private String[] columns = {"Vehicle ID", "Reg No", "Type", "PUC Expiry", "PUC Status", 
                                  "Insurance Expiry", "Insurance Status"};
        
        public ExpiryTableModel(ArrayList<Vehicle> vehicles) {
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
                case 3: return v.getPucExpiry();
                case 4: return getExpiryStatus(v.getPucExpiry());
                case 5: return v.getInsuranceExpiry();
                case 6: return getExpiryStatus(v.getInsuranceExpiry());
                default: return null;
            }
        }
        
        private String getExpiryStatus(String dateStr) {
            if (dateStr == null || dateStr.isEmpty()) return "No Date";
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date expiryDate = sdf.parse(dateStr);
                Date today = new Date();
                
                if (expiryDate.before(today)) {
                    return "EXPIRED!";
                }
                
                // Check if expiring within 30 days
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, 30);
                Date warningDate = cal.getTime();
                
                if (expiryDate.before(warningDate)) {
                    return "Expiring Soon!";
                }
                
                return "Valid";
            } catch (ParseException e) {
                return "Invalid Date";
            }
        }
    }
}
