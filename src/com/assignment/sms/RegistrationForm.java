package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfAddress;
    private JPasswordField tfPassword;
    private JTextField tfPhone;
    private JPasswordField tfConfirmPassword;
    private JButton btnSignUp;
    private JButton btnLogin;
    private JPanel registrationPanel;

    public RegistrationForm() {
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screen.width - 650) / 2, (screen.height - 400) / 2);
        frame.setContentPane(registrationPanel);
        frame.setVisible(true);
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(frame);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginForm();
            }
        });
    }

    public boolean validateForm(JTextField name, JTextField email, JTextField address, JTextField phone , JPasswordField psw, JPasswordField confirmPsw,  JPanel panel) {
        if (name.getText().isEmpty()) {
            showError("Name is required", panel);
            return false;
        }

        if (email.getText().isEmpty() || !tfEmail.getText().matches("^\\S+@\\S+\\.\\S+$")) {
            showError("Valid email is required", panel);
            return false;
        }

        if (address.getText().isEmpty()) {
            showError("Address is required", panel);
            return false;
        }

        String password = new String(psw.getPassword());
        String confirmPassword = new String(confirmPsw.getPassword());

        if (password.isEmpty()) {
            showError("Password is required", panel);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match", panel);
            return false;
        }

        if (phone.getText().isEmpty() || !phone.getText().matches("\\d+")) {
            showError("Valid phone number is required", panel);
            return false;
        }

        return true;
    }
    private void registerUser(JFrame frame) {
        String username = tfName.getText();
        String password = new String(tfPassword.getPassword());
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String confirmPassword = new String(tfConfirmPassword.getPassword());
        DatabaseOperations databaseOperations = new DatabaseOperations();
        FormServices formServices = new FormServices();
        if (formServices.validateForm(tfName, tfEmail, tfAddress, tfPhone, tfPassword, tfConfirmPassword,registrationPanel)) {
            user = databaseOperations.addUserToDatabase(username, password, address, phone, email);
            JOptionPane.showMessageDialog(frame, "User registered successfully");
            frame.dispose();
            new LoginForm();
        }


    }

    public User user;


    private void showError(String message, JPanel errorPanel) {
        JOptionPane.showMessageDialog(errorPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // public static void main(String[] args) {
    //     RegistrationForm form = new RegistrationForm();

    // }
}
