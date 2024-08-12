package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerGUI {
    private JFrame frame;

    public User user;
    public AddCustomerGUI(DefaultListModel<User> userListModel) {
        JFrame frame = new JFrame("Add Customer");
        JPanel addCustomerPanel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout()); 

        addCustomerPanel.setLayout(new BoxLayout(addCustomerPanel, BoxLayout.Y_AXIS)); // Correct layout for panel

        JLabel usernameLabel = new JLabel("Username:");
        JTextField tfUsername = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField tfEmail = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField tfAddress = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField tfPhone = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField tfPassword = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField tfConfirmPassword = new JPasswordField();
        JButton addButton = new JButton("Add");

        addCustomerPanel.add(usernameLabel);
        addCustomerPanel.add(tfUsername);
        addCustomerPanel.add(emailLabel);
        addCustomerPanel.add(tfEmail);
        addCustomerPanel.add(addressLabel);
        addCustomerPanel.add(tfAddress);
        addCustomerPanel.add(phoneLabel);
        addCustomerPanel.add(tfPhone);
        addCustomerPanel.add(passwordLabel);
        addCustomerPanel.add(tfPassword);
        addCustomerPanel.add(confirmPasswordLabel);
        addCustomerPanel.add(tfConfirmPassword);
        addCustomerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        addCustomerPanel.add(addButton);

        frame.add(addCustomerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(tfPassword.getPassword());
                String address = tfAddress.getText();
                String phone = tfPhone.getText();
                String email = tfEmail.getText();
                DatabaseOperations databaseOperations = new DatabaseOperations();
                FormServices formServices = new FormServices();
                if (formServices.validateForm(tfUsername, tfEmail, tfAddress, tfPhone, tfPassword, tfConfirmPassword,addCustomerPanel)) {
                    user = databaseOperations.addUserToDatabase(username, password, address, phone, email);
                }
                if (user != null) {
                    userListModel.addElement(user);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add customer. Please try again.");
                }
            }
        });
    }
}


