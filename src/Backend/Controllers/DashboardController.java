package Backend.Controllers;

import Backend.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardController {
    private Stage mainStage;

    @FXML
    private StackPane contentStackPane;

    @FXML
    private BorderPane mainDashboard;

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

            // Debug check (if it prints scene broke)
            if (mainStage == null) {
                System.out.println("Main stage is NULL! Cannot switch scenes.");
                return;
            }
    
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

            Object controller = loader.getController();
            if (controller instanceof DashboardController) {
                ((DashboardController) controller).setMainWindow(mainStage);
            }

            if (!contentStackPane.getChildren().isEmpty()) {
                Node currentPanel = contentStackPane.getChildren().get(0);

                if (currentPanel instanceof Region) {
                    Region currentRegion = (Region) currentPanel;

                    // Fix size to main center panel
                    panel.setPrefWidth(currentRegion.getWidth());
                    panel.setPrefHeight(currentRegion.getHeight());

                    // Bind size dynamically  Dynamically
                    panel.prefWidthProperty().bind(currentRegion.widthProperty());
                    panel.prefHeightProperty().bind(currentRegion.heightProperty());
                }
            }

            contentStackPane.getChildren().setAll(panel); // Switch to new panel
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
