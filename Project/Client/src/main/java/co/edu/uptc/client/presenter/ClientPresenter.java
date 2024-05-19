package co.edu.uptc.client.presenter;

import co.edu.uptc.client.net.ClientConnection;
import co.edu.uptc.client.view.*;
import com.google.gson.Gson;

import java.io.IOException;

public class ClientPresenter {
    private ClientConnection connection;
    private LoginView loginView;
    private TransactionView transactionView;
    private BudgetView budgetView;
    private FamilyGroupView familyGroupView;
    private ExportDataView exportDataView;
    private Gson gson;
    private String currentUser; // Store the current user

    public ClientPresenter() {
        connection = new ClientConnection("localhost", 1234);
        loginView = new LoginView(this);
        transactionView = new TransactionView(this);
        budgetView = new BudgetView(this);
        familyGroupView = new FamilyGroupView(this);
        exportDataView = new ExportDataView(this);
        gson = new Gson();
    }

    public void start() {
        try {
            connection.connect();
            loginView.display();
        } catch (IOException e) {
            loginView.showMessage("Error connecting to server: " + e.getMessage());
        }
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public TransactionView getTransactionView() {
        return transactionView;
    }

    public BudgetView getBudgetView() {
        return budgetView;
    }

    public FamilyGroupView getFamilyGroupView() {
        return familyGroupView;
    }

    public ExportDataView getExportDataView() {
        return exportDataView;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
