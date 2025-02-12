package Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Backend.BankEntities.Account;

public class AccountDetailsController {

    private Account currentAccount;

    @FXML
    private Label accBalanceLabel;

    @FXML
    private BorderPane mainBorderPane; 

    public void setMainWindow(Stage stage) {
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
        accBalanceLabel.setText("Balance: " + currentAccount.getBalance());
    }

    // Like dashboard center panal switch func
    private void loadCenterContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane newContent = loader.load();

            if (mainBorderPane != null) {
                Node centerNode = mainBorderPane.getCenter();

                if (centerNode instanceof Region) {
                    Region centerRegion = (Region) centerNode;

                    // Fix size to main center panel
                    newContent.setPrefWidth(centerRegion.getWidth());
                    newContent.setPrefHeight(centerRegion.getHeight());
                    
                    // Bind size dynamically 
                    newContent.prefWidthProperty().bind(centerRegion.widthProperty());
                    newContent.prefHeightProperty().bind(centerRegion.heightProperty());
                }

                mainBorderPane.setCenter(newContent); 
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
        // Logic to go back to the main dashboard
        System.out.println("Going back to accounts!");
    }


       // create withdraw

    //transfer money (deposit)

    // u could move the deleted account here
}
