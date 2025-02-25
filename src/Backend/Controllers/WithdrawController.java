package Backend.Controllers;

import Backend.BankEntities.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Backend.accountDOA;

public class WithdrawController {
    
    private Account account;
    private accountDOA accountDOA = new accountDOA();
    private AccountDetailsController accountDetailsController;

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

    private void withdrawAmount(double amount) {
        if (account == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No account selected for withdrawal.");
            return;
        }

        if (amount <= 0) {
            showAlert(Alert.AlertType.ERROR, "Invalid Withdrawal", "Withdraw amount must be greater than zero.");
            return;
        }

        if (amount > account.getBalance()) {
            showAlert(Alert.AlertType.ERROR, "Insufficient Funds", "You do not have enough balance for this withdrawal.");
            return;
        }
    
        String accountNum = account.getAccountNum();
        
        try {
            boolean success = accountDOA.withdrawToAccount(accountNum, amount);
            
            if (success) {
                account.setBalance(account.getBalance() - amount); 

                if (accountDetailsController != null) {
                    accountDetailsController.updateBalanceLabel(); 
                }

                showAlert(Alert.AlertType.INFORMATION, "Amount Withdrawn", "Transaction was successful.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during withdrawal.");
        }
    }

    @FXML
    private void handleWithdraw() {
        if (account == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No account selected for withdrawal.");
            return;
        }
    
        try {
            double amount = Double.parseDouble(customAmountTextField.getText());
            withdrawAmount(amount);

            if (amount > 0) {
                customAmountTextField.clear();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number.");
        }
    }
    
    @FXML
    private void handleWithdrawBtnPresets(javafx.event.ActionEvent event) {
        Button clickedBtn = (Button) event.getSource();
        String amountText = clickedBtn.getText().replace("$", ""); 
        double amount = Double.parseDouble(amountText);
    
        withdrawAmount(amount);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
