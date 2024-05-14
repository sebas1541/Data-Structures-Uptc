package co.edu.uptc.presenter;

import co.edu.uptc.models.Employees;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static Employees employees;

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
                String request = input.readUTF();
                switch (request) {
                    case "1":
                        handleEnterEmployees();
                        break;
                    case "2":
                        handleEnterHoursWorked();
                        break;
                    case "3":
                        handleDisplayFinalPayroll();
                        break;
                    case "4":
                        handleRemoveEmployee();
                        break;
                    case "5":
                        handleUpdateEmployeeHours();
                        break;
                    case "6":
                        handleDisplayAverageHoursWorked();
                        break;
                    case "7":
                        running = false;
                        break;
                }
            }
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

    private void handleEnterEmployees() throws IOException {
        synchronized (employees) {
            int wage = Integer.parseInt(input.readUTF());
            employees.setWagePerHour(wage);
            int count = Integer.parseInt(input.readUTF());
            List<String> workers = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                workers.add(input.readUTF());
            }
            employees.setWorkers(workers);
            output.writeUTF("Employees have been successfully added.");
        }
    }

    private void handleEnterHoursWorked() throws IOException {
        synchronized (employees) {
            List<String> workers = employees.getWorkers();
            output.writeUTF(String.valueOf(workers.size()));
            List<Integer> hours = new ArrayList<>();
            for (String worker : workers) {
                output.writeUTF(worker);
                int hoursWorked = Integer.parseInt(input.readUTF());
                hours.add(hoursWorked);
            }
            employees.setHoursWorked(hours);
            output.writeUTF("Hours worked have been successfully recorded.");
        }
    }

    private void handleDisplayFinalPayroll() throws IOException {
        synchronized (employees) {
            int payment = employees.calculatePayment();
            output.writeUTF("Total payroll for the period: " + payment);
        }
    }

    private void handleRemoveEmployee() throws IOException {
        synchronized (employees) {
            String workerName = input.readUTF();
            boolean success = employees.removeWorker(workerName);
            if (success) {
                output.writeUTF(workerName + " has been successfully removed.");
            } else {
                output.writeUTF("Employee not found. Please try again.");
            }
        }
    }

    private void handleUpdateEmployeeHours() throws IOException {
        synchronized (employees) {
            String workerName = input.readUTF();
            int hours = Integer.parseInt(input.readUTF());
            boolean success = employees.updateHoursWorked(workerName, hours);
            if (success) {
                output.writeUTF("Hours worked by " + workerName + " have been successfully updated.");
            } else {
                output.writeUTF("Employee not found. Please try again.");
            }
        }
    }

    private void handleDisplayAverageHoursWorked() throws IOException {
        synchronized (employees) {
            double averageHours = employees.calculateAverageHoursWorked();
            output.writeUTF("Average hours worked by employees: " + averageHours);
        }
    }
}
