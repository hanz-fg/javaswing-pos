package com.company.versamodeclothing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JFrame implements ActionListener {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton, createAccountButton;
    private JLabel messageLabel;

    // Static accounts HashMap to store usernames and passwords
    static final HashMap<String, String> accounts = new HashMap<>();

    static {
        // Default account
        accounts.put("user", "pass");
    }

    public LoginPage() {
        setTitle("Login");
        setSize(400, 300);
        setLayout(null);

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\Aileen Garcia\\OneDrive\\Documents\\NetBeansProjects\\versamodeclothing\\src\\main\\java\\img\\bg-loginpage.png")); // Replace with your image path
        background.setBounds(0, 0, 400, 300);
        add(background);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(50, 50, 100, 25);
        background.add(userLabel);

        userField = new JTextField();
        userField.setBounds(150, 50, 200, 25);
        background.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.BLACK);
        passLabel.setBounds(50, 100, 100, 25);
        background.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(150, 100, 200, 25);
        background.add(passField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 120, 30);
        loginButton.addActionListener(this);
        background.add(loginButton);

        createAccountButton = new JButton("Create an Account");
        createAccountButton.setBounds(180, 150, 170, 30);
        createAccountButton.addActionListener(e -> new CreateAccount());
        background.add(createAccountButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(50, 200, 300, 25);
        background.add(messageLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    String username = userField.getText();
    String password = new String(passField.getPassword());

    if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
        dispose(); // Close the login page
        
        // Prompt for gender selection
        String[] genders = {"Male", "Female"};
        String selectedGender = (String) JOptionPane.showInputDialog(
            this, 
            "Select Gender:", 
            "Gender Selection", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            genders, 
            genders[0]
        );
        
        // If user cancels the dialog, default to the first option
        if (selectedGender == null) {
            selectedGender = "Male";
        }
        
        // Open MenuPage with selected gender
        new MenuPage(selectedGender);
    } else {
        messageLabel.setText("Invalid credentials. Try again.");
    }
}

    public static void main(String[] args) {
        new LoginPage();
    }
}

class CreateAccount extends JFrame implements ActionListener {

    private JTextField newUserField;
    private JPasswordField newPassField;
    private JButton createButton;
    private JLabel messageLabel;

    public CreateAccount() {
        setTitle("Create Account");
        setSize(400, 300);
        setLayout(null);

        JLabel background = new JLabel(new ImageIcon("background.jpg")); // Replace with your image path
        background.setBounds(0, 0, 400, 300);
        add(background);

        JLabel userLabel = new JLabel("New Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 50, 100, 25);
        background.add(userLabel);

        newUserField = new JTextField();
        newUserField.setBounds(150, 50, 200, 25);
        background.add(newUserField);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 100, 100, 25);
        background.add(passLabel);

        newPassField = new JPasswordField();
        newPassField.setBounds(150, 100, 200, 25);
        background.add(newPassField);

        createButton = new JButton("Create Account");
        createButton.setBounds(50, 150, 300, 30);
        createButton.addActionListener(this);
        background.add(createButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(50, 200, 300, 25);
        background.add(messageLabel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newUsername = newUserField.getText();
        String newPassword = new String(newPassField.getPassword());

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            messageLabel.setText("Fields cannot be empty.");
            return;
        }

        if (LoginPage.accounts.containsKey(newUsername)) {
            messageLabel.setText("Username already exists.");
        } else {
            LoginPage.accounts.put(newUsername, newPassword);
            messageLabel.setText("Account created successfully.");
            dispose();
        }
    }
}
