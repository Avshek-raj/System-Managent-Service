package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewReportsGUI {
    private JFrame frame;

    public ViewReportsGUI() {
        frame = new JFrame("View Reports");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton revenueReportButton = new JButton("View Revenue Report");
        JButton workloadReportButton = new JButton("View Workload Distribution");
        JButton customerHistoryButton = new JButton("View Customer History");

        panel.add(revenueReportButton);
        panel.add(workloadReportButton);
        panel.add(customerHistoryButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        revenueReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String startDate = JOptionPane.showInputDialog("Enter start date (YYYY-MM-DD):");
//                String endDate = JOptionPane.showInputDialog("Enter end date (YYYY-MM-DD):");
                List<String> report = ReportManager.getRevenueReport();
                displayReport("Revenue Report", report);
            }
        });

        workloadReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> report = ReportManager.getWorkloadDistribution();
                displayReport("Workload Distribution", report);
            }
        });

        customerHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerIdStr = JOptionPane.showInputDialog("Enter customer ID:");
                int customerId = Integer.parseInt(customerIdStr);
                List<String> history = ReportManager.getCustomerHistory(customerId);
                displayReport("Customer History", history);
            }
        });
    }

    private void displayReport(String title, List<String> report) {
        JFrame reportFrame = new JFrame(title);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reportFrame.setSize(600, 400);
        reportFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (String line : report) {
            textArea.append(line + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        reportFrame.add(scrollPane, BorderLayout.CENTER);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
    }
}

