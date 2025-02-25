package Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Backend.BankEntities.Account;

public class AccountDetailsController {

    private Account currentAccount;

    @FXML
    private Label accBalanceLabel;

    @FXML
    private BorderPane mainBorderPane; 

    @FXML
    private Button backToAccountsBtn;

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
        updateBalanceLabel();
    }

    public void updateBalanceLabel() {
        if (currentAccount != null) {
            accBalanceLabel.setText("Balance: " + currentAccount.getBalance());
        }
    }

    private void loadCenterContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane newContent = loader.load();
    
            if (mainBorderPane != null) {
                Node centerNode = mainBorderPane.getCenter();
    
                if (centerNode instanceof Region) {
                    Region centerRegion = (Region) centerNode;
    
                    newContent.setPrefWidth(centerRegion.getWidth());
                    newContent.setPrefHeight(centerRegion.getHeight());
                    newContent.prefWidthProperty().bind(centerRegion.widthProperty());
                    newContent.prefHeightProperty().bind(centerRegion.heightProperty());
                }
    
                mainBorderPane.setCenter(newContent);
    
                Object controller = loader.getController();
                
                if (controller instanceof DepositController) {
                    ((DepositController) controller).setAccount(currentAccount);
                    ((DepositController) controller).setAccountDetailsController(this);
                } else if (controller instanceof WithdrawController) { 
                    ((WithdrawController) controller).setAccount(currentAccount);
                    ((WithdrawController) controller).setAccountDetailsController(this);
                }
    
            } else {
                System.err.println("Error: mainBorderPane not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    public void handleDepositBtn() {
        loadCenterContent("/Frontend/pages/depositScene.fxml");
    }

    @FXML
    public void handleWithdrawBtn() {
        loadCenterContent("/Frontend/pages/withdrawScene.fxml");
    }

    @FXML
    public void goBackToAccounts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/pages/dashboardscene.fxml"));
            BorderPane root = loader.load(); 
            
            DashboardController dashboardController = loader.getController();
            
            Stage stage = (Stage) backToAccountsBtn.getScene().getWindow();
            
            dashboardController.setMainWindow(stage);
            dashboardController.handleAccountBtnClick();
    
            stage.getScene().setRoot(root); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
