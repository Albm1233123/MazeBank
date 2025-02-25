package Backend.Controllers;

import Backend.BankEntities.Account;
import Backend.accountDOA;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DepositController {

    private Account account;
    private accountDOA accountDOA = new accountDOA();
    private AccountDetailsController accountDetailsController; // Reference to update balance

    @FXML
    private TextField customAmountTextField;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountDetailsController(AccountDetailsController controller) {
        this.accountDetailsController = controller;
    }

    public void setAccountDOA(accountDOA accountDOA) {
        this.accountDOA = accountDOA;
    }

    private void depositAmount(double amount) {
        if (account == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No account selected for deposit.");
            return;
        }

        if(amount <= 0) {
            showAlert(Alert.AlertType.ERROR, "Invalid Deposit", "Deposit amount must be greater than zero.");
            return;
        }
    
        String accountNum = account.getAccountNum();
        
        try {
            boolean success = accountDOA.depositToAccount(accountNum, amount);
            
            if (success) {
                account.setBalance(account.getBalance() + amount);
    
                if (accountDetailsController != null) {
                    accountDetailsController.updateBalanceLabel(); // Refresh UI
                }
            } 
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during deposit.");
        }
    }

    @FXML
    private void handleDeposit() {
        if (account == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No account selected for deposit.");
            return;
        }
    
        try {
            double amount = Double.parseDouble(customAmountTextField.getText());
            depositAmount(amount);

            if(amount > 0) {
                customAmountTextField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Amount Deposited", "Transaction was Successful");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number.");
        }
    }
    
    @FXML
    private void handleDepositBtnPresets(javafx.event.ActionEvent event) {
        Button clickedBtn = (Button) event.getSource();
        String amountText = clickedBtn.getText().replace("$", ""); 
        double amount = Double.parseDouble(amountText);
    
        depositAmount(amount); 

        showAlert(Alert.AlertType.INFORMATION, "Amount Deposited", "Transaction was Successful");
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
