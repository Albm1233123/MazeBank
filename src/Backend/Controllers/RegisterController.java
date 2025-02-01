package Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Backend.userDOA;

public class RegisterController {
    
    private Stage mainStage;
    private userDOA userdoa = new userDOA();

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField emailTextField;

    public void setMainWindow(Stage primaryStage) {
        this.mainStage = primaryStage;
    }

    @FXML
    public void handleBackBtnClick(){
        switchBackToLogin();
    }

    public void switchBackToLogin() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/pages/loginscene.fxml"));
            AnchorPane root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setMainWindow(mainStage); 

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
    public void createUser() {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Please fill in all fields");
        } else {
            userdoa.createUser(username, password, email);
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Registration Successful");
            switchBackToLogin();
        }
    }
}
