package com.company.versamodeclothing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Product class to represent clothing items


class ClothingCategory extends JFrame {
    static final ArrayList<Product> cart = new ArrayList<>();

    public ClothingCategory(String category, String gender) {
        // Set up the main frame
        setTitle(category + " Clothing for " + gender);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Create top panel
        JPanel topPanel = createTopPanel(category, gender);
        add(topPanel, BorderLayout.NORTH);

        // Create subcategory panel
        JPanel subcategoryPanel = createSubcategoryPanel(category, gender);
        add(new JScrollPane(subcategoryPanel), BorderLayout.CENTER);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private JPanel createTopPanel(String category, String gender) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        
        JLabel titleLabel = new JLabel(category + " Clothing for " + gender);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel createSubcategoryPanel(String category, String gender) {
        JPanel subcategoryPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        subcategoryPanel.setBackground(Color.WHITE);

        String[] subcategories = {"Headwear", "Top", "Bottom", "Footwear"};
        
        for (String subcategory : subcategories) {
            JButton subcategoryButton = createSubcategoryButton(subcategory, category, gender);
            subcategoryPanel.add(subcategoryButton);
        }

        return subcategoryPanel;
    }

    private JButton createSubcategoryButton(String subcategory, String category, String gender) {
        JButton button = new JButton(subcategory);
        
        // Customize button appearance
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 100));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.GRAY);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
            }
        });
        
        // Add action listener
        button.addActionListener(e -> showProducts(subcategory, category, gender));
        
        return button;
    }

    private void showProducts(String subcategory, String category, String gender) {
        JFrame productFrame = new JFrame(subcategory + " in " + category + " for " + gender);
        productFrame.setSize(800, 600);
        productFrame.setLayout(new BorderLayout(10, 10));
        productFrame.getContentPane().setBackground(Color.WHITE);

        // Create top panel for title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel(subcategory + " in " + category + " for " + gender);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        productFrame.add(topPanel, BorderLayout.NORTH);

        // Create product panel
        JPanel productPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (gender.equals("Men")) {
            displayProducts(productPanel, category, subcategory, true);
        } else { // For Women
            displayProducts(productPanel, category, subcategory, false);
        }

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBorder(null);
        productFrame.add(scrollPane, BorderLayout.CENTER);

        productFrame.setLocationRelativeTo(null); // Center on screen
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setVisible(true);
    }

    private void displayProducts(JPanel panel, String category, String subcategory, boolean isMen) {
        String prefix = isMen ? "Men's " : "Women's ";

        switch (category) {
            case "Business Attire":
                addFormalProducts(panel, subcategory, prefix);
                break;
            case "Casual Wear":
                addCasualProducts(panel, subcategory, prefix);
                break;
            case "Sportswear":
                addSportswearProducts(panel, subcategory, prefix);
                break;
            case "Streetwear":
                addStreetwearProducts(panel, subcategory, prefix);
                break;
        }
    }

    private void addFormalProducts(JPanel panel, String subcategory, String prefix) {
        switch (subcategory) {
            case "Headwear":
                addProduct(panel, new Product(
                    prefix + "Formal Hat", 
                    49.99, 
                    "images/formal-hat.jpg"
                ));
                break;
            case "Top":
                addProduct(panel, new Product(
                    prefix + "Formal Shirt", 
                    59.99, 
                    "C:\\Users\\Aileen Garcia\\OneDrive\\Documents\\NetBeansProjects\\versamodeclothing\\src\\main\\java\\img\\Clothing\\Business Attire\\Business Casual\\462571658_1093796205407767_2312112010084130494_n.jpg"
                ));
                break;
            case "Bottom":
                addProduct(panel, new Product(
                    prefix + "Formal Trousers", 
                    79.99, 
                    "images/formal-trousers.jpg"
                ));
                break;
            case "Footwear":
                addProduct(panel, new Product(
                    prefix + "Formal Shoes", 
                    89.99, 
                    "images/formal-shoes.jpg"
                ));
                break;
        }
    }

    private void addCasualProducts(JPanel panel, String subcategory, String prefix) {
        switch (subcategory) {
            case "Headwear":
                addProduct(panel, new Product(
                    prefix + "Casual Cap", 
                    19.99, 
                    "images/casual-cap.jpg"
                ));
                break;
            case "Top":
                addProduct(panel, new Product(
                    prefix + "Casual T-Shirt", 
                    29.99, 
                    "images/casual-tshirt.jpg"
                ));
                break;
            case "Bottom":
                addProduct(panel, new Product(
                    prefix + "Casual Shorts", 
                    39.99, 
                    "images/casual-shorts.jpg"
                ));
                break;
            case "Footwear":
                addProduct(panel, new Product(
                    prefix + "Casual Sneakers", 
                    59.99, 
                    "images/casual-sneakers.jpg"
                ));
                break;
        }
    }

    private void addSportswearProducts(JPanel panel, String subcategory, String prefix) {
        switch (subcategory) {
            case "Headwear":
                addProduct(panel, new Product(
                    prefix + "Sports Cap", 
                    15.99, 
                    "images/sports-cap.jpg"
                ));
                break;
            case "Top":
                addProduct(panel, new Product(
                    prefix + "Sports T-Shirt", 
                    29.99, 
                    "images/sports-tshirt.jpg"
                ));
                break;
            case "Bottom":
                addProduct(panel, new Product(
                    prefix + "Sports Shorts", 
                    39.99, 
                    "images/sports-shorts.jpg"
                ));
                break;
            case "Footwear":
                addProduct(panel, new Product(
                    prefix + "Sports Shoes", 
                    89.99, 
                    "images/sports-shoes.jpg"
                ));
                break;
        }
    }

    private void addStreetwearProducts(JPanel panel, String subcategory, String prefix) {
        switch (subcategory) {
            case "Headwear":
                addProduct(panel, new Product(
                    prefix + "Streetwear Beanie", 
                    25.99, 
                    "images/streetwear-beanie.jpg"
                ));
                break;
case "Top":
    addProduct(panel, new Product(
        prefix + "Streetwear Hoodie", 
        49.99, 
        "images/streetwear-hoodie.jpg"
    ));
    addProduct(panel, new Product(
        prefix + "Light Red Dress Shirt", 
        64.99, 
        "C:\\Users\\Aileen Garcia\\OneDrive\\Documents\\NetBeansProjects\\versamodeclothing\\src\\main\\java\\img\\Clothing\\Business Attire\\Business Casual\\462574512_1326812481819056_3486444982728987974_n.jpg"
    ));
    addProduct(panel, new Product(
        prefix + "Striped Formal Shirt", 
        69.99, 
        "C:\\Users\\Aileen Garcia\\OneDrive\\Documents\\NetBeansProjects\\versamodeclothing\\src\\main\\java\\img\\Clothing\\Business Attire\\Business Casual\\462574920_1129640608671875_4666860189120009074_n.jpg"
    ));
    
                break;
            case "Bottom":
                addProduct(panel, new Product(
                    prefix + "Streetwear Joggers", 
                    39.99, 
                    "images/streetwear-joggers.jpg"
                ));
                break;
            case "Footwear":
                addProduct(panel, new Product(
                    prefix + "Streetwear Sneakers", 
                    69.99, 
                    "images/streetwear-sneakers.jpg"
                ));
                break;
        }
    }

    private void addProduct(JPanel panel, Product product) {
        // Create a panel for each product to hold both image and details
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(Color.WHITE);

        // Add image if available
        if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
            try {
                // Load and scale the image
                ImageIcon originalIcon = new ImageIcon(product.getImagePath());
                Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                productPanel.add(imageLabel, BorderLayout.CENTER);
            } catch (Exception e) {
                // Fallback if image can't be loaded
                System.err.println("Could not load image: " + product.getImagePath());
            }
        }

        // Create product button
        JButton productButton = new JButton("<html><center>" + product.getName() + "<br>$" + String.format("%.2f", product.getPrice()) + "</center></html>");
        
        // Customize product button
        productButton.setFont(new Font("Arial", Font.BOLD, 14));
        productButton.setBackground(Color.DARK_GRAY);
        productButton.setForeground(Color.WHITE);
        productButton.setFocusPainted(false);
        productButton.setPreferredSize(new Dimension(250, 100));
        
        // Add hover effect
        productButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productButton.setBackground(Color.GRAY);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productButton.setBackground(Color.DARK_GRAY);
            }
        });
        
        // Add to cart action
        productButton.addActionListener(e -> {
            cart.add(product);
            JOptionPane.showMessageDialog(panel, product.getName() + " added to cart.");
        });
        
        // Add button to bottom of product panel
        productPanel.add(productButton, BorderLayout.SOUTH);
        
        // Add the entire product panel to the main panel
        panel.add(productPanel);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example of how to create a clothing category
            new ClothingCategory("Casual Wear", "Men");
        });
    }
}