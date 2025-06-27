package com.vts.ui.admin;

import javax.swing.*;

import com.vts.data.DataContext;
import com.vts.model.ExpenseReport;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ReportGenerationUI extends JFrame {
    private DataContext dataContext;
    private JTextField reportIdField, monthField;

    public ReportGenerationUI(DataContext dataContext) {
        this.dataContext = dataContext;
        setTitle("Generate Monthly Report");
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Report ID:"));
        reportIdField = new JTextField();
        panel.add(reportIdField);
        
        panel.add(new JLabel("Month (YYYY-MM):"));
        monthField = new JTextField();
        panel.add(monthField);
        
        JButton generateButton = new JButton("Generate Report");
        generateButton.addActionListener(this::generateReport);
        panel.add(generateButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
        
        add(panel);
    }

    private void generateReport(ActionEvent e) {
        String reportId = reportIdField.getText();
        String month = monthField.getText();
        
        if (reportId.isEmpty() || month.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter report ID and month");
            return;
        }
        
        // Validate month format (YYYY-MM)
        if (!month.matches("\\d{4}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Invalid month format. Use YYYY-MM (e.g., 2023-05)");
            return;
        }
        
        ExpenseReport report = dataContext.admin.generateMonthlyReport(
            reportId, month, 
            dataContext.fuelEntries, 
            dataContext.maintenanceRecords, 
            dataContext.accidentRecords
        );
        
        // Add report to data context
        dataContext.expenseReports.add(report);
        
        // Create and display report
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.append("======= Monthly Expense Report =======\n");
        textArea.append("Report ID: " + reportId + "\n");
        textArea.append("Month: " + month + "\n");
        textArea.append("Total Fuel Cost: ₹" + String.format("%.2f", report.getTotalFuelCost()) + "\n");
        textArea.append("Total Maintenance Cost: ₹" + String.format("%.2f", report.getTotalMaintenanceCost()) + "\n");
        textArea.append("Total Accident Cost: ₹" + String.format("%.2f", report.getTotalAccidentCost()) + "\n");
        textArea.append("-------------------------------------\n");
        textArea.append("Grand Total: ₹" + String.format("%.2f", report.getGrandTotal()) + "\n");
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
                                    "Expense Report", JOptionPane.INFORMATION_MESSAGE);
        
        // Close the report generation window
        dispose();
    }
}

