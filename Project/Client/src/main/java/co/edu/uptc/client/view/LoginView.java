package co.edu.uptc.client.view;

import co.edu.uptc.client.net.Request;
import co.edu.uptc.client.net.Response;
import co.edu.uptc.client.net.UserData;
import co.edu.uptc.client.presenter.ClientPresenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class LoginView {
    private ClientPresenter presenter;
    private Gson gson;
    private UserData currentUser;

    public LoginView(ClientPresenter presenter) {
        this.presenter = presenter;
        this.gson = new Gson();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Finance Manager!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        String option = scanner.nextLine();

        try {
            switch (option) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                default:
                    showMessage("Invalid option. Try again.");
                    display();
                    break;
            }
        } catch (IOException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        LoginData loginData = new LoginData(username, password);
        String loginDataJson = gson.toJson(loginData);
        Request loginRequest = new Request("login", loginDataJson);
        presenter.getConnection().sendRequest(loginRequest);

        Response response = presenter.getConnection().receiveResponse();
        if ("success".equals(response.getStatus())) {
            currentUser = new UserData(username, password, null);
            presenter.getTransactionView().display();
        } else {
            showMessage("Error: " + response.getData());
            display();
        }
    }

    private void register() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        UserData userData = new UserData(username, password, email);
        String userDataJson = gson.toJson(userData);
        Request registerRequest = new Request("register", userDataJson);
        presenter.getConnection().sendRequest(registerRequest);

        Response response = presenter.getConnection().receiveResponse();
        if ("success".equals(response.getStatus())) {
            showMessage("User registered successfully. Please login.");
            login();
        } else {
            showMessage("Error: " + response.getData());
            display();
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public UserData getCurrentUser() {
        return currentUser;
    }
}
