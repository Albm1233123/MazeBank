package Backend.Controllers;

import Backend.BankEntities.User;
import Backend.UserSession;
import Backend.userDOA;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
    private Stage mainStage;
    private userDOA userdoa = new userDOA();
    
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    // Sets the main Stage
    public void setMainWindow(Stage primaryStage) {
        this.mainStage = primaryStage;
    }

    @FXML
    public void handleRegisterButtonClick() {
        switchToRegisterScene();
    }

    public void switchToRegisterScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/pages/registrationscene.fxml"));
            AnchorPane root = loader.load();

            RegisterController registerController = loader.getController();
            registerController.setMainWindow(mainStage);

            Scene newScene = new Scene(root, 1080, 720);
            mainStage.setScene(newScene);
            mainStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleLogin() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (userdoa.validateUserCredentials(username, password)) {
            User currentUser = userdoa.getUserByUsername(username);

            // Store the logged-in user globally using UserSession
            UserSession.setCurrentUser(currentUser);

            switchToDashboardScene();
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + username);
        } else if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Please fill in all fields");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void switchToDashboardScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/pages/dashboardscene.fxml"));
            BorderPane root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setMainWindow(mainStage);

            Scene dashboardScene = new Scene(root, 1080, 720);
            mainStage.setScene(dashboardScene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
