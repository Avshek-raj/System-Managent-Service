package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm {
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JButton btnLogin;
    private JButton btnSignUp;
    private JPanel loginPanel;
    JFrame frame = new JFrame("Login");
    public LoginForm() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screen.width - 650) / 2, (screen.height - 400) / 2);
        frame.setContentPane(loginPanel);
        frame.setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(tfPassword.getPassword());
                authenticateUser(username, password);

            }
        });
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegistrationForm();

            }
        });
    }

    public User user;
    private void authenticateUser(String username, String password) {
            User user = null;

            final String DATABASE_URL = "jdbc:mysql://localhost/servicemanagementsystem";
            final String Username = "root";
            final String Password = "";

            try {
                Connection conn = DriverManager.getConnection(DATABASE_URL, Username, Password);
                String selectUserQuery = "SELECT * From Users WHERE Username = '" + username + "' AND Password = '"+ password + "'";
                PreparedStatement preparedStatement = conn.prepareStatement(selectUserQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    String type = resultSet.getString("type");
                    user = new User(id, username, password,type, address, phone, email);
                }
                if (user!= null) {
                    if (user.type.toLowerCase().equals("customer")) {
                        new CustomerGUI(user);
                    }else {
                        new StaffGUI(user);
                    }
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                conn.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
