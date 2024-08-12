package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseServicesGUI {


    private JFrame frame;
    private DefaultListModel<Service> serviceListModel;
    private JList<Service> serviceList;
    User user;

    public BrowseServicesGUI(User user) {
        this.user = user;
        frame = new JFrame("Services");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        serviceListModel = new DefaultListModel<>();
        serviceList = new JList<>(serviceListModel);
        serviceList.setCellRenderer(new BrowseServicesGUI.ServiceListCellRenderer());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        JButton requestQuoteButton = new JButton("Request Quote");

        buttonsPanel.add(requestQuoteButton);
        frame.add(new JScrollPane(serviceList), BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        List<Service> services = ServiceManager.getAllServices();
        for (Service service : services) {
            serviceListModel.addElement(service);
        }

        requestQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Service selectedService = serviceList.getSelectedValue();
                    if (selectedService != null) {
                        QuoteManager.addQuoteToDatabase(user.getId(), selectedService.getId(), selectedService.getName(), "Pending", user.getUsername());
                        JOptionPane.showMessageDialog(frame, "Quote requested successfully for " + selectedService.getName());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a service to request quote.");
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
        SwingUtilities.invokeLater(() -> new BrowseServicesGUI(null));
    }
}

