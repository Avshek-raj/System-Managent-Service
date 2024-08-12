package com.assignment.sms;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewHistoryGUI {
    private JFrame frame;
    private User customer;

    public ViewHistoryGUI(User customer) {
        this.customer = customer;
        frame = new JFrame("View History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Service Request History");
        panel.add(label, BorderLayout.NORTH);

        JTextArea historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);

        List<ServiceRequest> history = ServiceRequestManager.getServiceRequestsByCustomerId(customer.getId());
        StringBuilder historyText = new StringBuilder();
        for (ServiceRequest request : history) {
            historyText.append("Date: ").append(request.getDate()).append("\n")
                    .append("Service: ").append(request.getServiceName()).append("\n")
                    .append("Cost: $").append(request.getCost()).append("\n")
                    .append("Status: ").append(request.getStatus()).append("\n\n");
        }
        historyTextArea.setText(historyText.toString());

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

