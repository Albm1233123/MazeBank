package Frontend;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public BaseFrame(String title) {
        super(title);
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Panels
        cardPanel.add(new userStart(this), "Login"); 
        cardPanel.add(new userRegister(this), "Register"); 
        cardPanel.add(new dashBoard(this), "DashBoard"); 
        
        add(cardPanel, BorderLayout.CENTER);
    }

    // Switch to login panel
    public void showLogin() {
        cardLayout.show(cardPanel, "Login");
    }

    // Switch to register panel
    public void showRegister() {
        cardLayout.show(cardPanel, "Register");
    }

    public void showDashboard() {
        cardLayout.show(cardPanel, "DashBoard");
    }
}
