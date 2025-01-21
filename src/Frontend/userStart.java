package Frontend;

import Backend.userDOA;
import javax.swing.*;
import java.awt.*;

public class userStart extends JPanel {

    private userDOA userFuncs;
    private BaseFrame baseFrame; 

    public userStart(BaseFrame baseFrame) {
        this.baseFrame = baseFrame; 
        userFuncs = new userDOA();

        JPanel panel = new JPanel();

        // Username input
        JLabel usernameLabel = new JLabel("Full Name:");
        JTextField usernameTextField = new JTextField(20);

        // Email input
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(15);

        // Password Input
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordTextField = new JPasswordField(15);

        JButton logButton = new JButton("Login");

        // No account? 
        JLabel registerLabel = new JLabel("New to MazeBank?");
        JButton registerButton = new JButton("Register");

        // Actions
        logButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = new String(passwordTextField.getPassword());

            if (userFuncs.validateUserCredentials(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Switch to main dashboard
                //baseFrame.showDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });

        registerButton.addActionListener(e -> {
            baseFrame.showRegister(); 
        });

        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(logButton);
        panel.add(registerLabel);
        panel.add(registerButton);

        add(panel, BorderLayout.CENTER);
    }
}
