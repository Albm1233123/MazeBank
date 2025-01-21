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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Username ID
        Random rand = new Random();
        int userId = 1000 + rand.nextInt(9000); // generate random user id (temp func)
        JLabel userIdLabel = new JLabel("Your user ID: " + userId);
        JLabel instructionLabel = new JLabel("Please save this ID for future reference!");

        // Username input
        JLabel usernameLabel = new JLabel("Full Name:");
        JTextField usernameTextField = new JTextField(20);

        // Email input
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(15);

        // Password Input
        JLabel passwordJLabel = new JLabel("Password:");
        JPasswordField passwordTextField = new JPasswordField(15);

        // Register button
        JButton registerButton = new JButton("Register");

        // Back to login
        JButton backButton = new JButton("back to Login");

        // Actions
        registerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            baseFrame.showLogin();
        });

        backButton.addActionListener( e-> {
            baseFrame.showLogin();
        });


        panel.add(userIdLabel);
        panel.add(instructionLabel);
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(passwordJLabel);
        panel.add(passwordTextField);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);
    }
}
