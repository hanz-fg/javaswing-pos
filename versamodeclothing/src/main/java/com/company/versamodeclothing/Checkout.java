/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.versamodeclothing;

/**
 *
 * @author Hanz Garcia
 */
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checkout extends JFrame {
    private JTextField nameField, addressField, phoneField, emailField;
    private JButton submitButton, printWaybillButton;
    private JLabel messageLabel;
    private String orderDetails;

    public Checkout() {
        // Set up the frame with a more modern look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Order Checkout");
        setSize(500, 400);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        // Create constraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components with improved styling
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(labelFont);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            nameField.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(nameField, gbc);

        // Address Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel addressLabel = new JLabel("Shipping Address:");
        addressLabel.setFont(labelFont);
        add(addressLabel, gbc);

        gbc.gridx = 1;
        addressField = new JTextField(20);
        addressField.setFont(fieldFont);
        addressField.setBorder(BorderFactory.createCompoundBorder(
            addressField.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(addressField, gbc);

        // Phone Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFont);
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        phoneField = new JTextField(20);
        phoneField.setFont(fieldFont);
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            phoneField.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(phoneField, gbc);

        // Email Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            emailField.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(emailField, gbc);

        // Submit Button
        submitButton = new JButton("Place Order");
        submitButton.setFont(labelFont);
        submitButton.setBackground(new Color(76, 175, 80));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> submitCheckout());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Print Waybill Button (initially disabled)
        printWaybillButton = new JButton("Print Waybill");
        printWaybillButton.setFont(labelFont);
        printWaybillButton.setBackground(new Color(33, 150, 243));
        printWaybillButton.setForeground(Color.WHITE);
        printWaybillButton.setFocusPainted(false);
        printWaybillButton.setEnabled(false);
        printWaybillButton.addActionListener(e -> printWaybill());
        gbc.gridy = 5;
        add(printWaybillButton, gbc);

        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 6;
        add(messageLabel, gbc);

        // Final frame setup
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void submitCheckout() {
        // Validate input
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        // Input validation
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            messageLabel.setText("All fields must be filled.");
            return;
        }

        // Validate email format (basic check)
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            messageLabel.setText("Invalid email format.");
            return;
        }

        // Generate order details
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderDetails = String.format(
            "Order Details:\n" +
            "Name: %s\n" +
            "Address: %s\n" +
            "Phone: %s\n" +
            "Email: %s\n" +
            "Order Date: %s\n" +
            "Tracking Number: %s",
            name, address, phone, email, 
            sdf.format(new Date()),
            generateTrackingNumber()
        );

        // Show success message
        JOptionPane.showMessageDialog(this, 
            "Order placed successfully!", 
            "Order Confirmation", 
            JOptionPane.INFORMATION_MESSAGE);

        // Enable print waybill button
        printWaybillButton.setEnabled(true);
        messageLabel.setText("Order placed. You can now print the waybill.");
    }

    private void printWaybill() {
        // Check if order details exist
        if (orderDetails == null) {
            JOptionPane.showMessageDialog(this, 
                "Please place an order first.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Print the waybill
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new Printable() {
                public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                    if (page > 0) return NO_SUCH_PAGE;

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.translate(pf.getImageableX(), pf.getImageableY());

                    // Draw waybill content
                    Font titleFont = new Font("Arial", Font.BOLD, 16);
                    Font contentFont = new Font("Arial", Font.PLAIN, 12);
                    
                    g2d.setFont(titleFont);
                    g2d.drawString("Shipping Waybill", 50, 50);

                    g2d.setFont(contentFont);
                    String[] lines = orderDetails.split("\n");
                    for (int i = 0; i < lines.length; i++) {
                        g2d.drawString(lines[i], 50, 100 + (i * 20));
                    }

                    return PAGE_EXISTS;
                }
            });

            // Display print dialog
            if (job.printDialog()) {
                job.print();
                JOptionPane.showMessageDialog(this, 
                    "Waybill printed successfully!", 
                    "Print Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error printing waybill: " + ex.getMessage(), 
                "Print Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Generate a random tracking number
    private String generateTrackingNumber() {
        return String.format("%08d", (int)(Math.random() * 100000000));
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Checkout().setVisible(true);
        });
    }
}
