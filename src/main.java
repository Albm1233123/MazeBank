import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import Frontend.BaseFrame;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
                BaseFrame baseFrame = new BaseFrame("Maze Bank");
                baseFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
