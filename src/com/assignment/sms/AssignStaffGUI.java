package com.assignment.sms;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignStaffGUI {
    private JFrame frame;

    private DefaultListModel<User> staffListModel;
    private JList<User> staffList;

    public AssignStaffGUI(Quote quote) {
        frame = new JFrame("Assign Staff");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        staffListModel = new DefaultListModel<>();
        staffList = new JList<>(staffListModel);
        staffList.setCellRenderer(new UserListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(staffList);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1));

        JButton addButton = new JButton("Assign");

        buttonsPanel.add(addButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate user list
        List<User> users = UserManager.getAllUsers();
        for (User staff : users) {
            if (staff.getType().toLowerCase().equals("staff")) {
                staffListModel.addElement(staff);
            }
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedStaff = staffList.getSelectedValue();
                if (selectedStaff != null) {
                    QuoteManager.assignStaff(selectedStaff.id, selectedStaff.username, quote.getId());
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a staff to assign.");
                }
            }
        });
    }

    private static class UserListCellRenderer extends JPanel implements ListCellRenderer<User> {

        private JLabel usernameLabel;
        private JLabel emailLabel;
        private JLabel addressLabel;
        private JLabel phoneLabel;
        private JLabel staffIdLabel;

        private JSeparator separator;

        public UserListCellRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            staffIdLabel = new JLabel();
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
            staffIdLabel.setText("Staff Id: " + value.getId());
            usernameLabel.setText("Staff name: " + value.getUsername());
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
        new AssignStaffGUI(null);
    }
}

