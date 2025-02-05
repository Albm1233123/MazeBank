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
    private ListView<Account> accountListView;
    private Account currentSelectedAccount = null;
    private ObservableList<Account> accountList = FXCollections.observableArrayList();
    

    @FXML
    private Label accountNumLabel;

    @FXML
    private TextField balanceTextField;

    @FXML
    private TextField accountNameTextField;

    @FXML
    private Button createAccountButton;

    private accountDOA accountDAO = new accountDOA();

    @FXML
    public void initialize() {
        accountListView.setItems(accountList);

        // Load loggedin user accounts
        loadUserAccounts();
        
        // Selected Account listener
        accountListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(currentSelectedAccount)) {
                    System.out.println("Re-selected the same account: " + newValue.getAccountName());
                } else {
                    currentSelectedAccount = newValue;
                    System.out.println("Selected Account: " + newValue.getAccountName());
                }
            }
        });
    }    

    private void loadUserAccounts() {
        User currentUser = UserSession.getCurrentUser();    
        if (currentUser != null) {
            List<Account> accounts = accountDAO.getAccountsByUserId(currentUser.getUserId());
            accountList.clear();
            
            System.out.println("Accounts retrieved: " + accounts.size());  // Debugging line
            
            accountList.addAll(accounts); // Store actual Account objects
            accountListView.setItems(accountList); // Set ListView to hold Account objects
            
            // Set how the ListView displays the accounts
            accountListView.setCellFactory(param -> new ListCell<Account>() {
                @Override
                protected void updateItem(Account account, boolean empty) {
                    super.updateItem(account, empty);
                    if (empty || account == null) {
                        setText(null);
                    } else {
                        setText("Account: " + account.getAccountNum() + " - " + account.getAccountName() + " ($" + account.getBalance() + ")");
                    }
                }
            });
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

    @SuppressWarnings("unlikely-arg-type")
    @FXML
    private void deleteAccount() {
        if (currentSelectedAccount != null) {

            if(currentSelectedAccount.getBalance() != 0) {
                showAlert("Error", "Account balance must be 0 before deletion");
                return;
            }
            
            // Confirm deletion
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText("Are you sure you want to delete this account?");
            confirmAlert.setContentText("This action cannot be undone.");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Ensure the account is deleted from the database
                    accountDAO.deleteAccount(currentSelectedAccount.getAccountNum());

                    // Remove from the UI list
                    accountList.remove(currentSelectedAccount);
                    currentSelectedAccount = null;

                    showAlert("Success", "Account deleted successfully!");
                }
            });
        } else {
            showAlert("Error", "No account selected for deletion.");
        }
    }  
}
