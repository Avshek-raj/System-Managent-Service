package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageServicesGUI {
    private JFrame frame;
    private DefaultListModel<Service> serviceListModel;
    private JList<Service> serviceList;

    public ManageServicesGUI() {
        frame = new JFrame("Manage Services");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        serviceListModel = new DefaultListModel<>();
        serviceList = new JList<>(serviceListModel);
        serviceList.setCellRenderer(new ServiceListCellRenderer());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        JButton addButton = new JButton("Add Service");
        JButton deleteButton = new JButton("Delete Service");

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);

        frame.add(new JScrollPane(serviceList), BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        List<Service> services = ServiceManager.getAllServices();
        for (Service service : services) {
            serviceListModel.addElement(service);
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddServiceGUI(serviceListModel);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Service selectedService = serviceList.getSelectedValue();
                if (selectedService != null) {
                    ServiceManager.deleteService(selectedService.getId());
                    serviceListModel.removeElement(selectedService);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a service to delete.");
                }
            }
        });
    }

    private static class ServiceListCellRenderer extends JPanel implements ListCellRenderer<Service> {

        private JLabel nameLabel;
        private JLabel descriptionLabel;
        private JLabel estimatedDurationLabel;
        private JLabel costLabel;
        private JSeparator separator;
        public ServiceListCellRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            nameLabel = new JLabel();
            descriptionLabel = new JLabel();
            estimatedDurationLabel = new JLabel();
            costLabel = new JLabel();
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(nameLabel);
            add(descriptionLabel);
            add(estimatedDurationLabel);
            add(costLabel);
            add(separator);

        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Service> list, Service value, int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText("Name: " + value.getName());
            descriptionLabel.setText("Description: " + value.getDescription());
            estimatedDurationLabel.setText("Estimated Duration in hr: " + value.getEstimatedDuration());
            costLabel.setText("Cost: " + value.getCost());

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
        SwingUtilities.invokeLater(() -> new ManageServicesGUI());
    }
}

class AddServiceGUI {
    private JFrame frame;

    public AddServiceGUI(DefaultListModel<Service> serviceListModel) {
        frame = new JFrame("Add Service");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JLabel durationLabel = new JLabel("Estimated Duration:");
        JTextField durationField = new JTextField();
        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();
        JButton addButton = new JButton("Add");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(descriptionLabel);
        frame.add(descriptionField);
        frame.add(durationLabel);
        frame.add(durationField);
        frame.add(costLabel);
        frame.add(costField);
        frame.add(new JLabel());
        frame.add(addButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                int duration = Integer.parseInt(durationField.getText());
                double cost = Double.parseDouble(costField.getText());

                Service service = ServiceManager.addServiceToDatabase(name, description, duration, cost);
                if (service != null) {
                    serviceListModel.addElement(service);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add service. Please try again.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new ManageServicesGUI();
    }
}

