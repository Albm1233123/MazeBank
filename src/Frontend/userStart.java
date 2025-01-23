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

        // GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username input
        JLabel usernameLabel = new JLabel("Full Name:");
        JTextField usernameTextField = new JTextField(20);
        usernameTextField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(usernameLabel, gbc);
        gbc.gridy = 1;
        add(usernameTextField, gbc);

        // Password Input
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordTextField = new JPasswordField(20);
        passwordTextField.setPreferredSize(new Dimension(200, 25));
        gbc.gridy = 4;
        add(passwordLabel, gbc);
        gbc.gridy = 5;
        add(passwordTextField, gbc);

        // Login button
        JButton logButton = new JButton("Login");
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logButton, gbc);

        // Spacer
        JPanel spacer = new JPanel();
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(spacer, gbc);

        // No account?
        JLabel registerLabel = new JLabel("New to MazeBank?");
        JButton registerButton = new JButton("Register");
        gbc.gridy = 8;
        add(registerLabel, gbc);
        gbc.gridy = 9;
        add(registerButton, gbc);

        // Actions for buttons
        logButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = new String(passwordTextField.getPassword());

            if (userFuncs.validateUserCredentials(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                baseFrame.showDashboard();

            } else if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });

        registerButton.addActionListener(e -> {
            baseFrame.showRegister();
        });
    }
}
