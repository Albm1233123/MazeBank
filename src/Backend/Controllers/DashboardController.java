package Backend.Controllers;

import Backend.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardController {
    private Stage mainStage;

    @FXML
    private StackPane contentStackPane;

    public void setMainWindow(Stage primaryStage) {
        this.mainStage = primaryStage;
    }

    @FXML
    public void handleLogoutBtnClick() {
        showLogoutConfirmation();
    }

    private void showLogoutConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");
        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonYes) {
                logout();
            }
        });
    }

    private void logout() {
        // Ensure user is logged out
        UserSession.clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/pages/loginscene.fxml"));
            AnchorPane root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setMainWindow(mainStage);
            Scene newScene = new Scene(root, 1080, 720);
            mainStage.setScene(newScene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Panel switching
    private void loadDashPanels(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane panel = loader.load();
    
            contentStackPane.getChildren().setAll(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    public void handleProfileBtnClick() {
        loadDashPanels("/Frontend/pages/profilescene.fxml");
    }

    @FXML
    public void handleAccountBtnClick() {
        loadDashPanels("/Frontend/pages/accountscene.fxml");
    }
}
