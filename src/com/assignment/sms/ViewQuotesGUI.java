package com.assignment.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewQuotesGUI {
    private JFrame frame;
    private DefaultListModel<Quote> quoteListModel;
    private JList<Quote> quoteList;

    public ViewQuotesGUI() {
        frame = new JFrame("View Quotes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        quoteListModel = new DefaultListModel<>();
        quoteList = new JList<>(quoteListModel);
        quoteList.setCellRenderer(new ViewQuotesGUI.QuoteListCellRenderer());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");
        JButton assignButton = new JButton("Assign");

        buttonsPanel.add(acceptButton);
        buttonsPanel.add(rejectButton);
        buttonsPanel.add(assignButton);

        frame.add(new JScrollPane(quoteList), BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate quote list
        List<Quote> quotes = QuoteManager.getAllQuotes();
        for (Quote quote : quotes) {
            if (quote.getStatus().toLowerCase().equals("accepted")){

            }else {
                quoteListModel.addElement(quote);
            }

        }

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Quote selectedQuote = quoteList.getSelectedValue();
                if (selectedQuote != null) {
                  QuoteManager.changeQuoteStatus("Accepted", selectedQuote.getId());
                  AppointmentManager.addAppointmentInDatabase(selectedQuote.getCustomerId(), selectedQuote.getServiceId(), selectedQuote.getServiceName(), selectedQuote.getAssignedStaffId(), selectedQuote.getAssignedStaffName(), selectedQuote.getCustomerName());
                    quoteListModel.removeElement(selectedQuote);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a quote to accept.");
                }
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Quote selectedQuote = quoteList.getSelectedValue();
                if (selectedQuote != null) {
                    QuoteManager.changeQuoteStatus("Rejected", selectedQuote.getId());
                    quoteListModel.removeElement(selectedQuote);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a quote to reject.");
                }
            }
        });

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Quote selectedQuote = quoteList.getSelectedValue();
                if (selectedQuote != null) {
                    new AssignStaffGUI(selectedQuote);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a quote to assign.");
                }
            }
        });
    }

    private static class QuoteListCellRenderer extends JPanel implements ListCellRenderer<Quote> {

        private JLabel serviceLabel;
        private JLabel customerIdLabel;
        private JLabel customerNameLabel;
        private JLabel statusLabel;
        private JLabel assignedStaff;
        private JLabel assignedStaffId;
        private JSeparator separator;
        public QuoteListCellRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            serviceLabel = new JLabel();
            customerIdLabel = new JLabel();
            customerNameLabel = new JLabel();
            statusLabel = new JLabel();
            assignedStaff = new JLabel();
            assignedStaffId = new JLabel();
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(serviceLabel);
            add(customerIdLabel);
            add(customerNameLabel);
            add(statusLabel);
            add(assignedStaffId);
            add(assignedStaff);
            add(separator);

        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Quote> list, Quote value, int index, boolean isSelected, boolean cellHasFocus) {
            serviceLabel.setText("Service: " + value.getServiceName());
            customerIdLabel.setText("Customer Id: " + value.getCustomerId());
            customerNameLabel.setText("Customer Name: " + value.getCustomerName());
            statusLabel.setText("Status: " + value.getStatus());
            if (value.getAssignedStaffName() != null) {
                assignedStaffId.setText("Assigned staff: " + value.getAssignedStaffId());
                assignedStaff.setText("Assigned staff: " + value.getAssignedStaffName());
            }else {
                assignedStaff.setText("Assigned staff: ");
            }

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
        new ViewQuotesGUI();
    }
}
