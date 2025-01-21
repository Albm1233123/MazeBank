import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import Frontend.BaseFrame;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf()); // Set the look and feel to FlatDarkLaf
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace in case of an exception
            }
            
            // Create and display the BaseFrame window
            try {
                BaseFrame baseFrame = new BaseFrame("Maze Bank");
                baseFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace if BaseFrame fails to load
            }
        });
    }
}
