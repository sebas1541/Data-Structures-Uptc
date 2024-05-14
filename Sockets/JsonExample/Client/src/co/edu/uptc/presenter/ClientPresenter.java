package co.edu.uptc.presenter;

import co.edu.uptc.net.ClientConnection;
import co.edu.uptc.net.Request;
import co.edu.uptc.net.Response;
import co.edu.uptc.views.View;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientPresenter {
    private ClientConnection connection;
    private View view;
    private Gson gson;

    public ClientPresenter() {
        view = new View();
        connection = new ClientConnection("localhost", 1234);
        gson = new Gson();
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
                if ("7".equals(option)) {
                    running = false;
                } else {
                    handleOption(option);
                }
                Response response = connection.receiveResponse();
                view.showMessage("Response from server: " + response.getData());
            } catch (IOException e) {
                view.showMessage("Error communicating with server: " + e.getMessage());
                running = false;
            }
        }
        closeConnection();
    }

    private void handleOption(String option) throws IOException {
        RequestData requestData = new RequestData();
        switch (option) {
            case "1":
                int wage = view.readInt("Enter hourly wage:");
                requestData.setWage(wage);
                int count = view.readInt("Enter number of employees:");
                List<String> workers = new ArrayList<>();
                for (int i = 1; i <= count; i++) {
                    String name = view.readString("Enter name of employee " + i + ":");
                    workers.add(name);
                }
                requestData.setWorkers(workers);
                String data1 = gson.toJson(requestData);
                Request request1 = new Request(option, data1);
                connection.sendRequest(request1);
                break;
            case "2":
                connection.sendRequest(new Request("2", ""));
                Response response = connection.receiveResponse();
                if (!response.getStatus().equals("success")) {
                    view.showMessage("Error: " + response.getData());
                    return;
                }
                int numWorkers;
                try {
                    numWorkers = Integer.parseInt(response.getData());
                } catch (NumberFormatException e) {
                    view.showMessage("Invalid number format from server.");
                    return;
                }
                List<Integer> hours = new ArrayList<>();
                for (int i = 1; i <= numWorkers; i++) {
                    response = connection.receiveResponse();
                    if (!response.getStatus().equals("worker")) {
                        view.showMessage("Error: " + response.getData());
                        return;
                    }
                    String worker = response.getData();
                    int hoursWorked = view.readInt("Enter hours worked by " + worker + ":");
                    hours.add(hoursWorked);
                }
                requestData.setHours(hours);
                String data2 = gson.toJson(requestData);
                Request request2 = new Request("2_update", data2);
                connection.sendRequest(request2);
                break;
            case "3":
                Request request3 = new Request("3", "");
                connection.sendRequest(request3);
                break;
            case "4":
                String workerName = view.readString("Enter the name of the employee to remove:");
                requestData.setWorkerName(workerName);
                String data4 = gson.toJson(requestData);
                Request request4 = new Request(option, data4);
                connection.sendRequest(request4);
                break;
            case "5":
                String name = view.readString("Enter the name of the employee to update hours:");
                requestData.setWorkerName(name);
                int hoursWorked = view.readInt("Enter the new hours worked:");
                requestData.setHoursWorked(hoursWorked);
                String data5 = gson.toJson(requestData);
                Request request5 = new Request(option, data5);
                connection.sendRequest(request5);
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
