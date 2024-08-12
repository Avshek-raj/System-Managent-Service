package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAppointmentsGUI {
    private JFrame frame;

    private DefaultListModel<Appointment> appointmentListModel;
    private JList<Appointment> appointmentList;

    public ViewAppointmentsGUI(User user) {
        frame = new JFrame("Appointments");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        appointmentListModel = new DefaultListModel<>();
        appointmentList = new JList<>(appointmentListModel);
        appointmentList.setCellRenderer(new AppointmentListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(appointmentList);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1));
        JButton addButton;
        if (user.getType().toLowerCase().equals("staff")) {
            addButton = new JButton("Schedule Appointment");
        } else {
            addButton = new JButton("Reschedule");
        }

        buttonsPanel.add(addButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate user list
        List<Appointment> appointments = AppointmentManager.getAllAppointments();
        for (Appointment appointment : appointments) {
            if (user.getType().toLowerCase().equals("staff")) {
                if (appointment.getAssignedStaffId() == user.getId()) {
                    appointmentListModel.addElement(appointment);
                }
            } else {
                if (appointment.getCustomerId() == user.getId()) {
                    appointmentListModel.addElement(appointment);
                }
            }
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointment selectedAppointment = appointmentList.getSelectedValue();
                if (selectedAppointment != null) {
                    new ScheduleAppointmentsGUI(selectedAppointment);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a appointment to schedule.");
                }
            }
        });
    }

    private static class AppointmentListCellRenderer extends JPanel implements ListCellRenderer<Appointment> {

        private JLabel customerNameLabel;
        private JLabel serviceNameLabel;
        private JLabel dateLabel;
        private JLabel timeLabel;
        private JLabel assignedStaffLabel;

        private JSeparator separator;

        public AppointmentListCellRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            customerNameLabel = new JLabel();
            serviceNameLabel = new JLabel();
            dateLabel = new JLabel();
            timeLabel = new JLabel();
            assignedStaffLabel = new JLabel();
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(customerNameLabel);
            add(serviceNameLabel);
            add(dateLabel);
            add(timeLabel);
            add(assignedStaffLabel);
            add(separator);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Appointment> list, Appointment value, int index, boolean isSelected, boolean cellHasFocus) {
            customerNameLabel.setText("Customer name: " + value.getCustomerName());
            serviceNameLabel.setText("Service: " + value.getServiceName());
            if (value.getDate() == null) {
                dateLabel.setText("Date: ");
            } else {
                dateLabel.setText("Date: " + value.getDate());
            }
            if (value.getTime() ==null) {
                timeLabel.setText("Time: ");
            } else {
                timeLabel.setText("Time: " + value.getTime());
            }
            assignedStaffLabel.setText("Assigned staff: " + value.getAssignedStaffMember());

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

