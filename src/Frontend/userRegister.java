package Frontend;

import Backend.userDOA;
import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class userRegister extends JPanel {
    
    private userDOA userFuncs;
    private BaseFrame baseFrame; 

    public userRegister(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;
        userFuncs = new userDOA();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        

        // Username ID
        Random rand = new Random();
        int userId = 1000 + rand.nextInt(9000); // generate random user id (temp func)
        JLabel userIdLabel = new JLabel("Your user ID: " + userId);
        JLabel instructionLabel = new JLabel("Please save this ID for future reference!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(userIdLabel, gbc);
        gbc.gridy = 1;
        add(instructionLabel, gbc);

        // Username input
        JLabel usernameLabel = new JLabel("Full Name:");
        JTextField usernameTextField = new JTextField(20);
        gbc.gridy = 2;
        add(usernameLabel, gbc);
        gbc.gridy = 3;
        add(usernameTextField, gbc);

        // Email input
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(20);
        gbc.gridy = 4;
        add(emailLabel, gbc);
        gbc.gridy = 5;
        add(emailTextField, gbc);

        // Password Input
        JLabel passwordJLabel = new JLabel("Password:");
        JPasswordField passwordTextField = new JPasswordField(20);
        gbc.gridy = 6;
        add(passwordJLabel, gbc);
        gbc.gridy = 7;
        add(passwordTextField, gbc);

        // Spacer
        JPanel spacer = new JPanel();
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(spacer, gbc);

        // Bottom btns
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        GridBagConstraints btns = new GridBagConstraints();
        btns.gridx = 0; 
        btns.gridy = 9;
        btns.gridwidth = 2;
        btns.anchor = GridBagConstraints.CENTER; 
        btns.insets = new Insets(5, 5, 5, 5); 
        add(buttonPanel, btns);

        // Actions
        registerButton.addActionListener(e -> {
            String username = usernameTextField.getText().trim();
            String passwordHash = passwordTextField.getText().trim();
            String email = emailTextField.getText().trim();

            if(username.isEmpty() || passwordHash.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");

            } else if (!username.isEmpty() || !passwordHash.isEmpty() || !email.isEmpty()) {
                userFuncs.createUser(username, passwordHash, email);
                JOptionPane.showMessageDialog(this, "Registration successful!");
                baseFrame.showLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed, please try again");
            }
        });

        backButton.addActionListener( e-> {
            baseFrame.showLogin();
        });
    }
}
