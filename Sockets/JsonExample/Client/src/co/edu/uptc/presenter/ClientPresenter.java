package co.edu.uptc.presenter;

import co.edu.uptc.views.View;
import co.edu.uptc.net.ClientConnection;

import java.io.IOException;

public class ClientPresenter {
    private ClientConnection connection;
    private View view;

    public ClientPresenter() {
        view = new View();
        connection = new ClientConnection("localhost", 1234);
        try {
            connection.connect();
            runClient();
        } catch (IOException e) {
            view.showMessage("Error connecting to server: " + e.getMessage());
        }
    }

    private void runClient() {
        boolean running = true;
        while (running) {
            String option = view.readString("Main Menu:\n1. Enter Employees\n2. Enter Hours Worked\n3. Finalize Payroll\n4. Remove Employee\n5. Update Hours Worked\n6. Display Average Hours Worked\n7. Exit\n\nSelect an option:");
            try {
                connection.sendRequest(option);
                if ("7".equals(option)) {
                    running = false;
                } else {
                    handleOption(option);
                }
                String response = connection.receiveResponse();
                view.showMessage(response);
            } catch (IOException e) {
                view.showMessage("Error communicating with server: " + e.getMessage());
                running = false;
            }
        }
        closeConnection();
    }

    private void handleOption(String option) throws IOException {
        switch (option) {
            case "1":
                int wage = view.readInt("Enter hourly wage:");
                connection.sendRequest(String.valueOf(wage));
                int count = view.readInt("Enter number of employees:");
                connection.sendRequest(String.valueOf(count));
                for (int i = 1; i <= count; i++) {
                    String name = view.readString("Enter name of employee " + i + ":");
                    connection.sendRequest(name);
                }
                break;
            case "2":
                int numWorkers = Integer.parseInt(connection.receiveResponse());
                for (int i = 1; i <= numWorkers; i++) {
                    String worker = connection.receiveResponse();
                    int hours = view.readInt("Enter hours worked by " + worker + ":");
                    connection.sendRequest(String.valueOf(hours));
                }
                break;
            case "4":
                String workerName = view.readString("Enter the name of the employee to remove:");
                connection.sendRequest(workerName);
                break;
            case "5":
                String name = view.readString("Enter the name of the employee to update hours:");
                connection.sendRequest(name);
                int hours = view.readInt("Enter the new hours worked:");
                connection.sendRequest(String.valueOf(hours));
                break;
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (IOException e) {
            view.showMessage("Error closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClientPresenter();
    }
}
