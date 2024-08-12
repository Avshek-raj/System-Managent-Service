package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StaffGUI {
    private JFrame frame;
    private User staff;

    public StaffGUI(User staff) {
        this.staff = staff;

        frame = new JFrame("Staff Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton manageCustomersButton = new JButton("Manage Customers");
        JButton manageServicesButton = new JButton("Manage Services");
        JButton viewQuotesButton = new JButton("View Quotes");
        JButton scheduleAppointmentsButton = new JButton("Schedule Appointments");
        JButton viewReportsButton = new JButton("View Reports");

        panel.add(manageCustomersButton);
        panel.add(manageServicesButton);
        panel.add(viewQuotesButton);
        panel.add(scheduleAppointmentsButton);
        panel.add(viewReportsButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        manageCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCustomersGUI();
            }
        });

        manageServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageServicesGUI();
            }
        });

        viewQuotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewQuotesGUI();
            }
        });
//
        scheduleAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAppointmentsGUI(staff);
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewReportsGUI();
            }
        });
    }

    public static void main(String[] args) {
        new StaffGUI(null);
    }
}

