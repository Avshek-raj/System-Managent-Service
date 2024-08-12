package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI {
    private JFrame frame;
    private User customer;

    public CustomerGUI(User customer) {
        this.customer = customer;
        frame = new JFrame("Customer Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        frame.setLayout(new GridLayout(3, 2));

        JButton browseServicesButton = new JButton("Browse Services");
        JButton viewAppointmentsButton = new JButton("View Appointments");
        JButton viewHistoryButton = new JButton("View History");

        frame.add(browseServicesButton);
        frame.add(viewAppointmentsButton);
        frame.add(viewHistoryButton);

        browseServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrowseServicesGUI(customer);
            }
        });

        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAppointmentsGUI(customer);
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewHistoryGUI(customer);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerGUI(null);
    }

}
