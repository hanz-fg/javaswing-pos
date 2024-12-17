package com.company.versamodeclothing;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends JFrame {
    public MenuPage(String gender) {
        // Set up the main frame
        setTitle(gender + " Clothing Store");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create side dashboard
        JPanel sideDashboard = createSideDashboard(gender);
        
        // Create top panel for header
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel(gender + " Clothing Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        
        // Create bottom panel for checkout and logout
        JPanel bottomPanel = createBottomPanel();
        
        // Add panels to main panel
        mainPanel.add(sideDashboard, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Center the frame on screen
        setLocationRelativeTo(null);
        
        // Make frame visible
        setVisible(true);
    }
    
    private JPanel createSideDashboard(String gender) {
        JPanel sideDashboard = new JPanel();
        sideDashboard.setLayout(new GridLayout(4, 1, 10, 10));
        sideDashboard.setBackground(Color.LIGHT_GRAY);
        sideDashboard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Define category buttons with custom styling
        String[] categories = {
            "Business Attire", 
            "Sportswear", 
            "Streetwear", 
            "Casual Wear"
        };
        
        for (String categoryName : categories) {
            JButton categoryButton = createCategoryButton(categoryName, gender);
            sideDashboard.add(categoryButton);
        }
        
        return sideDashboard;
    }
    
    private JButton createCategoryButton(String categoryName, String gender) {
        JButton categoryButton = new JButton(categoryName);
        
        // Customize button appearance
        categoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        categoryButton.setBackground(Color.DARK_GRAY);
        categoryButton.setForeground(Color.WHITE);
        categoryButton.setFocusPainted(false);
        
        // Add hover effect
        categoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categoryButton.setBackground(Color.GRAY);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categoryButton.setBackground(Color.DARK_GRAY);
            }
        });
        
        // Add action listener
        categoryButton.addActionListener(e -> new ClothingCategory(categoryName, gender));
        
        return categoryButton;
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        
        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.addActionListener(e -> showCart());
        
        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
        
        bottomPanel.add(checkoutButton);
        bottomPanel.add(logoutButton);
        
        return bottomPanel;
    }
    
    private void showCart() {
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(400, 300);
        cartFrame.setLayout(new BorderLayout());
        cartFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        
        if (ClothingCategory.cart.isEmpty()) {
            cartPanel.add(new JLabel("Your cart is empty."));
        } else {
            for (Product product : ClothingCategory.cart) {
                cartPanel.add(new JLabel(product.getName() + " - $" + product.getPrice()));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        cartFrame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> cartFrame.dispose());
        buttonPanel.add(closeButton);
        
        JButton proceedButton = new JButton("Proceed to Checkout");
        proceedButton.addActionListener(e -> {
            cartFrame.dispose();
            new Checkout();
        });
        buttonPanel.add(proceedButton);
        
        cartFrame.add(buttonPanel, BorderLayout.SOUTH);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setVisible(true);
    }
}