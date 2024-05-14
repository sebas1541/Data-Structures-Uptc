package co.edu.uptc.presenter;

import co.edu.uptc.models.Employees;
import co.edu.uptc.net.Request;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static Employees employees;
    private Gson gson = new Gson();

    public ClientThread(Socket socket, Employees employees) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        ClientThread.employees = employees;
    }

    @Override
    public void run() {
        try {
            boolean running = true;
            while (running) {
                String requestJson = input.readUTF();
                System.out.println("Received request: " + requestJson); // Logging
                Request request = gson.fromJson(requestJson, Request.class);
                switch (request.getAction()) {
                    case "1":
                        handleEnterEmployees(request.getData());
                        break;
                    case "2":
                        handleEnterHoursWorked();
                        break;
                    case "2_update":
                        handleUpdateHoursWorked(request.getData());
                        break;
                    case "3":
                        handleDisplayFinalPayroll();
                        break;
                    case "4":
                        handleRemoveEmployee(request.getData());
                        break;
                    case "5":
                        handleUpdateEmployeeHours(request.getData());
                        break;
                    case "6":
                        handleDisplayAverageHoursWorked();
                        break;
                    case "7":
                        running = false;
                        break;
                    default:
                        String responseJson = gson.toJson(new Response("error", "Invalid action"));
                        output.writeUTF(responseJson);
                }
            }
        } catch (SocketException e) {
            // Handle client disconnect
            System.out.println("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleEnterEmployees(String data) throws IOException {
        synchronized (employees) {
            RequestData requestData = gson.fromJson(data, RequestData.class);
            int wage = requestData.getWage();
            employees.setWagePerHour(wage);
            List<String> workers = requestData.getWorkers();
            employees.setWorkers(workers);
            String responseJson = gson.toJson(new Response("success", "Employees have been successfully added"));
            output.writeUTF(responseJson);
        }
    }

    private void handleEnterHoursWorked() throws IOException {
        synchronized (employees) {
            List<String> workers = employees.getWorkers();
            // Send the number of workers first
            String responseJson = gson.toJson(new Response("success", String.valueOf(workers.size())));
            output.writeUTF(responseJson);
            // Send each worker's name
            for (String worker : workers) {
                responseJson = gson.toJson(new Response("worker", worker));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleUpdateHoursWorked(String data) throws IOException {
        synchronized (employees) {
            RequestData requestData = gson.fromJson(data, RequestData.class);
            List<Integer> hoursWorked = requestData.getHours();
            employees.setHoursWorked(hoursWorked);
            String responseJson = gson.toJson(new Response("success", "Hours worked have been successfully recorded"));
            output.writeUTF(responseJson);
        }
    }

    private void handleDisplayFinalPayroll() throws IOException {
        synchronized (employees) {
            int payment = employees.calculatePayment();
            String responseJson = gson.toJson(new Response("success", "Total payroll for the period: " + payment));
            output.writeUTF(responseJson);
        }
    }

    private void handleRemoveEmployee(String data) throws IOException {
        synchronized (employees) {
            RequestData requestData = gson.fromJson(data, RequestData.class);
            String workerName = requestData.getWorkerName();
            boolean success = employees.removeWorker(workerName);
            String responseJson = success
                    ? gson.toJson(new Response("success", workerName + " has been successfully removed"))
                    : gson.toJson(new Response("error", "Employee not found. Please try again"));
            output.writeUTF(responseJson);
        }
    }

    private void handleUpdateEmployeeHours(String data) throws IOException {
        synchronized (employees) {
            RequestData requestData = gson.fromJson(data, RequestData.class);
            String workerName = requestData.getWorkerName();
            int hours = requestData.getHoursWorked();
            boolean success = employees.updateHoursWorked(workerName, hours);
            String responseJson = success
                    ? gson.toJson(new Response("success", "Hours worked by " + workerName + " have been successfully updated"))
                    : gson.toJson(new Response("error", "Employee not found. Please try again"));
            output.writeUTF(responseJson);
        }
    }

    private void handleDisplayAverageHoursWorked() throws IOException {
        synchronized (employees) {
            double averageHours = employees.calculateAverageHoursWorked();
            String responseJson = gson.toJson(new Response("success", "Average hours worked by employees: " + averageHours));
            output.writeUTF(responseJson);
        }
    }
}
