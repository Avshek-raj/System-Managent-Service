package com.assignment.sms;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageCustomersGUI {
    private JFrame frame;

    private DefaultListModel<User> userListModel;
    private JList<User> userList;

    public ManageCustomersGUI() {
        frame = new JFrame("Manage Customers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setCellRenderer(new UserListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(userList);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));

        JButton addButton = new JButton("Add Customer");
        JButton deleteButton = new JButton("Delete Customer");

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate user list
        List<User> users = UserManager.getAllUsers();
        for (User user : users) {
            if (user.getType().equals("Customer")) {
                userListModel.addElement(user);
            }
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCustomerGUI(userListModel);
            }
        });
//
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    UserManager.deleteUser(selectedUser.id);
                    userListModel.removeElement(selectedUser);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a user to delete.");
                }
            }
        });
    }

    private static class UserListCellRenderer extends JPanel implements ListCellRenderer<User> {

        private JLabel usernameLabel;
        private JLabel emailLabel;
        private JLabel addressLabel;
        private JLabel phoneLabel;
        private JSeparator separator;

        public UserListCellRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            usernameLabel = new JLabel();
            emailLabel = new JLabel();
            addressLabel = new JLabel();
            phoneLabel = new JLabel();
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(usernameLabel);
            add(emailLabel);
            add(addressLabel);
            add(phoneLabel);
            add(separator);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
            usernameLabel.setText("Username: " + value.getUsername());
            emailLabel.setText("Email: " + value.getEmail());
            addressLabel.setText("Address: " + value.getAddress());
            phoneLabel.setText("Phone: " + value.getPhone());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }
    public static void main(String[] args) {
        new ManageCustomersGUI();
    }
}

