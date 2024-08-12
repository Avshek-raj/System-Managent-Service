package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleAppointmentsGUI {
    private JFrame frame;

    public ScheduleAppointmentsGUI(Appointment selectedAppointment) {
        frame = new JFrame("Schedule Appointment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel dateLabel = new JLabel("Date: (yyyy-MM-dd)");
        JTextField dateField = new JTextField();
        if (selectedAppointment.getDate() != null) {
            dateField.setText(selectedAppointment.getDate());
        }
        JLabel timeLabel = new JLabel("Time: (HH:mm:ss)");
        JTextField timeField = new JTextField();
        if (selectedAppointment.getTime() != null) {
            timeField.setText(selectedAppointment.getTime());
        }
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);

        JButton scheduleButton = new JButton("Set Date and Time");

        frame.add(panel, BorderLayout.CENTER);
        frame.add(scheduleButton, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();

                if (selectedAppointment.getDate() != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date existingDate = dateFormat.parse(selectedAppointment.getDate());
                        Date currentSetDate = dateFormat.parse(date);
                        if (limitReschedule(existingDate, currentSetDate)) {
                            JOptionPane.showMessageDialog(frame, "The reschedule date is excessive. Reschedule is allowed for only 2 days.");
                        } else {
                            Boolean success = AppointmentManager.scheduleAppointment(selectedAppointment.getId(), date, time);
                            if (success ) {
                                JOptionPane.showMessageDialog(frame, "Appointment scheduled successfully.");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Failed to schedule appointment.");
                            }
                        }
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    Boolean success = AppointmentManager.scheduleAppointment(selectedAppointment.getId(), date, time);
                    if (success ) {
                        JOptionPane.showMessageDialog(frame, "Appointment scheduled successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to schedule appointment.");
                    }
                }
            }
        });
    }

    private static boolean limitReschedule(Date existingDate, Date currentSetDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(existingDate);

        // Add 2 days to the existing date
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date dateAfterTwoDays = calendar.getTime();
        return currentSetDate.after(dateAfterTwoDays);
    }

    public static void main(String[] args) {
        new ScheduleAppointmentsGUI(null);
    }
}

