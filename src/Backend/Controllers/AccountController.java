package Backend.Controllers;

import java.util.List;
import Backend.UserSession;
import Backend.accountDOA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Backend.BankEntities.Account;
import Backend.BankEntities.User;

public class AccountController {

    @FXML
    private ListView<String> accountListView;

    @FXML
    private Label accountNumLabel;

    @FXML
    private TextField balanceTextField;

    @FXML
    private TextField accountNameTextField;

    @FXML
    private Button createAccountButton;

    private accountDOA accountDAO = new accountDOA();
    private ObservableList<String> accountList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        accountListView.setItems(accountList);
        loadUserAccounts();
    }

    private void loadUserAccounts() {
        User currentUser = UserSession.getCurrentUser();    
        if (currentUser != null) {
            List<Account> accounts = accountDAO.getAccountsByUserId(currentUser.getUserId());
            accountList.clear();
            
            System.out.println("Accounts retrieved: " + accounts.size());  // Debugging line
            
            for (Account account : accounts) {
                accountList.add("Account: " + account.getAccountNum() + " - " + account.getAccountName() + " ($" + account.getBalance() + ")");
            }
        } else {
            showAlert("Error", "No user is logged in.");
        }
    }
    
    @FXML
    private void createAccount() {
        User currentUser = UserSession.getCurrentUser();  // Get the current user from UserSession
        System.out.println("Current user in session: " + currentUser);
        if (currentUser == null) {
            showAlert("Error", "No user is logged in.");
            return;
        }

        String accountName = accountNameTextField.getText();
        String balanceText = balanceTextField.getText();

        if (accountName.isEmpty() || balanceText.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        try {
            double balance = Double.parseDouble(balanceText);
            String accountNum = generateAccountNumber();

            int userId = currentUser.getUserId();

            accountDAO.createAccount(accountNum, balance, userId, accountName);

            loadUserAccounts();

            accountNumLabel.setText("Account Number: " + accountNum);
            accountNameTextField.clear();
            balanceTextField.clear();

            showAlert("Success", "Account created successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid balance. Please enter a valid number.");
        }
    }

    private String generateAccountNumber() {
        return "ACC" + (int) (Math.random() * 10000);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
