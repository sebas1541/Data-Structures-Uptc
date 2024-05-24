package co.edu.uptc.client.presenter;

import co.edu.uptc.client.net.*;
import co.edu.uptc.client.view.*;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ClientPresenter {
    private ClientConnection connection;
    private MainFrame mainFrame;
    private Gson gson;
    private String currentUser;

    public ClientPresenter() {
        connection = new ClientConnection("localhost", 1234);
        gson = new Gson();
        mainFrame = new MainFrame(this);
        start();
    }

    public void start() {
        try {
            connection.connect();
            mainFrame.setVisible(true);
            showLoginView(); // Ensure LoginView is shown on start
        } catch (IOException e) {
            mainFrame.getLoginView().showMessage("Error connecting to server: " + e.getMessage());
        }
    }

    public void login(String username, String password) throws IOException {
        LoginData loginData = new LoginData(username, password);
        String loginDataJson = gson.toJson(loginData);
        Request loginRequest = new Request("login", loginDataJson);
        connection.sendRequest(loginRequest);
        Response response = connection.receiveResponse();
        if ("success".equals(response.getStatus())) {
            currentUser = username;
            mainFrame.setUsername(username); // Set the username in MainFrame
            showTransactionView();
        } else {
            mainFrame.getLoginView().showMessage("Error: " + response.getData());
        }
    }

    public void register(String username, String password, String email) throws IOException {
        UserData userData = new UserData(username, password, email);
        String userDataJson = gson.toJson(userData);
        Request registerRequest = new Request("register", userDataJson);
        connection.sendRequest(registerRequest);
        Response response = connection.receiveResponse();
        if ("success".equals(response.getStatus())) {
            mainFrame.getRegisterView().showMessage("User registered successfully. Please login.");
            showLoginView();
        } else {
            mainFrame.getRegisterView().showMessage("Error: " + response.getData());
        }
    }

    public void addTransaction(String category, double amount, String description, String type) throws IOException {
        TransactionData transactionData = new TransactionData(currentUser, null, amount, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), category, description, type);
        String transactionDataJson = gson.toJson(transactionData);
        Request request = new Request("addTransaction", transactionDataJson);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getTransactionView().showMessage(response.getData());
        viewTransactions();
    }

    public void viewTransactions() throws IOException {
        viewTransactions(response -> {
            mainFrame.getTransactionView().showTransactions(response.getData());
        });
    }

    public void viewTransactions(Consumer<Response> callback) throws IOException {
        if (connection == null) {
            throw new IllegalStateException("Connection not established");
        }
        Request request = new Request("viewTransactions", currentUser);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        System.out.println("Transactions response: " + response.getData()); // Debugging statement
        callback.accept(response);
    }

    public void editTransaction(String transactionId, String category, double amount, String description, String type) throws IOException {
        TransactionData transactionData = new TransactionData(currentUser, transactionId, amount, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), category, description, type);
        String transactionDataJson = gson.toJson(transactionData);
        Request request = new Request("editTransaction", transactionDataJson);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getTransactionView().showMessage(response.getData());
        viewTransactions();
    }

    public void deleteTransaction(String transactionId) throws IOException {
        String requestData = currentUser + "," + transactionId;
        Request request = new Request("deleteTransaction", requestData);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getTransactionView().showMessage(response.getData());
        viewTransactions();
    }

    public void addBudget(String category, double amount) throws IOException {
        BudgetData budgetData = new BudgetData(currentUser, null, category, amount);
        String budgetDataJson = gson.toJson(budgetData);
        Request request = new Request("addBudget", budgetDataJson);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getBudgetView().showMessage(response.getData());
        viewBudgets();
        // Call loadBudgets() to refresh the list after adding a budget
        mainFrame.getBudgetView().getEditBudgetPanel().loadBudgets();
    }

    public void viewBudgets() throws IOException {
        viewBudgets(response -> {
            if ("success".equals(response.getStatus())) {
                mainFrame.getBudgetView().showBudgets(response.getData());
            } else {
                mainFrame.getBudgetView().showMessage("Error: " + response.getData());
            }
        });
    }

    public void viewBudgets(Consumer<Response> callback) throws IOException {
        if (connection == null) {
            throw new IllegalStateException("Connection not established");
        }
        Request request = new Request("viewBudget", currentUser); // Corrected action name
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        System.out.println("Budgets response: " + response.getData()); // Debugging statement
        callback.accept(response);
    }

    public void editBudget(String budgetId, String category, double amount) throws IOException {
        BudgetData budgetData = new BudgetData(currentUser, budgetId, category, amount);
        String budgetDataJson = gson.toJson(budgetData);
        Request request = new Request("editBudget", budgetDataJson);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getBudgetView().showMessage(response.getData());
        viewBudgets();
        // Call loadBudgets() to refresh the list after editing a budget
        mainFrame.getBudgetView().getEditBudgetPanel().loadBudgets();
    }

    public void deleteBudget(String budgetId) throws IOException {
        String requestData = currentUser + "," + budgetId;
        Request request = new Request("deleteBudget", requestData);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getBudgetView().showMessage(response.getData());
        viewBudgets();
        // Call loadBudgets() to refresh the list after deleting a budget
        mainFrame.getBudgetView().getEditBudgetPanel().loadBudgets();
    }

    public void addFamilyMember(String username, String email) throws IOException {
        FamilyMemberData familyMemberData = new FamilyMemberData(currentUser, username, email);
        String familyMemberDataJson = gson.toJson(familyMemberData);
        Request request = new Request("addMember", familyMemberDataJson);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getFamilyGroupView().showMessage(response.getData());
        viewFamilyMembers();
    }

    public void viewFamilyMembers() throws IOException {
        viewFamilyMembers(response -> {
            mainFrame.getFamilyGroupView().getViewFamilyMembersPanel().showFamilyMembers(response.getData());
        });
    }

    public void viewFamilyMembers(Consumer<Response> callback) throws IOException {
        if (connection == null) {
            throw new IllegalStateException("Connection not established");
        }
        Request request = new Request("viewMembers", currentUser);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        System.out.println("Family members response: " + response.getData()); // Debugging statement
        callback.accept(response);
    }

    public void exportData(String format) throws IOException {
        Request request = new Request("exportData", format);
        connection.sendRequest(request);
        Response response = connection.receiveResponse();
        mainFrame.getExportDataView().showMessage(response.getData());
    }

    public void showTransactionView() {
        mainFrame.showView("TransactionView");
    }

    public void showBudgetView() {
        mainFrame.showView("BudgetView");
    }

    public void showFamilyGroupView() {
        mainFrame.showView("FamilyGroupView");
    }

    public void showExportDataView() {
        mainFrame.showView("ExportDataView");
    }

    public void showLoginView() {
        mainFrame.showView("LoginView");
    }

    public void showRegisterView() {
        mainFrame.showView("RegisterView");
    }

    public void showAddFamilyMemberView() {
        mainFrame.getFamilyGroupView().showPanel("AddFamilyMemberPanel");
    }

    public void showViewFamilyMembersView() {
        mainFrame.getFamilyGroupView().showPanel("ViewFamilyMembersPanel");
    }
}
