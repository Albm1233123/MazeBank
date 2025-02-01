package Backend.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class DashboardController {
    private Stage mainStage;

    public void setMainWindow(Stage primaryStage) {
        this.mainStage = primaryStage;
    }
}
