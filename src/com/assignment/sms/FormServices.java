package com.assignment.sms;


import javax.swing.*;

class FormServices {
     private void showError(String message, JPanel errorPanel) {
        JOptionPane.showMessageDialog(errorPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public boolean validateForm(JTextField name, JTextField email, JTextField address, JTextField phone , JPasswordField psw, JPasswordField confirmPsw,  JPanel panel) {
        if (name.getText().isEmpty()) {
            showError("Name is required", panel);
            return false;
        }

        if (email.getText().isEmpty() || !email.getText().matches("^\\S+@\\S+\\.\\S+$")) {
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
}