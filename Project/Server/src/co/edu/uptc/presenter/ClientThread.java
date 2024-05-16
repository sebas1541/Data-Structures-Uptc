package co.edu.uptc.presenter;

import co.edu.uptc.model.*;
import co.edu.uptc.net.*;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static UserManager userManager;
    private Gson gson = new Gson();

    public ClientThread(Socket socket, UserManager userManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        ClientThread.userManager = userManager;
    }

    @Override
    public void run() {
        try {
            boolean running = true;
            while (running) {
                String requestJson = input.readUTF();
                System.out.println("Received request: " + requestJson); // Logging JSON
                Request request = gson.fromJson(requestJson, Request.class);
                switch (request.getAction()) {
                    case "addTransaction":
                        handleAddTransaction(request.getData());
                        break;
                    case "updateBudget":
                        handleUpdateBudget(request.getData());
                        break;
                    case "generateReport":
                        handleGenerateReport(request.getData());
                        break;
                    case "addFamilyMember":
                        handleAddFamilyMember(request.getData());
                        break;
                    case "removeFamilyMember":
                        handleRemoveFamilyMember(request.getData());
                        break;
                    case "disconnect":
                        running = false;
                        break;
                    default:
                        String responseJson = gson.toJson(new Response("error", "Invalid action"));
                        output.writeUTF(responseJson);
                }
            }
        } catch (SocketException e) {
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

    private void handleAddTransaction(String data) throws IOException {
        synchronized (userManager) {
            TransactionData transactionData = gson.fromJson(data, TransactionData.class);
            try {
                User user = userManager.getUserById(transactionData.getUserId());
                Transaction transaction = new Transaction(
                        transactionData.getTransactionId(),
                        transactionData.getAmount(),
                        transactionData.getDate(),
                        transactionData.getCategory(),
                        transactionData.getDescription(),
                        transactionData.getType()
                );
                user.addTransaction(transaction);
                String responseJson = gson.toJson(new Response("success", "Transaction added successfully"));
                output.writeUTF(responseJson);
            } catch (Exception e) {
                String responseJson = gson.toJson(new Response("error", e.getMessage()));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleUpdateBudget(String data) throws IOException {
        synchronized (userManager) {
            BudgetData budgetData = gson.fromJson(data, BudgetData.class);
            User user = userManager.getUserById(budgetData.getUserId());
            Budget budget = new Budget(
                    budgetData.getBudgetId(),
                    budgetData.getCategory(),
                    budgetData.getAmount(),
                    budgetData.getStartDate(),
                    budgetData.getEndDate()
            );
            user.addBudget(budget);
            String responseJson = gson.toJson(new Response("success", "Budget updated successfully"));
            output.writeUTF(responseJson);
        }
    }

    private void handleGenerateReport(String data) throws IOException {
        // Implementation for generating a report
    }

    private void handleAddFamilyMember(String data) throws IOException {
        synchronized (userManager) {
            FamilyMemberData familyMemberData = gson.fromJson(data, FamilyMemberData.class);
            User user = userManager.getUserById(familyMemberData.getUserId());
            User familyMember = userManager.getUserById(familyMemberData.getFamilyMemberId());
            user.addFamilyMember(familyMember);
            String responseJson = gson.toJson(new Response("success", "Family member added successfully"));
            output.writeUTF(responseJson);
        }
    }

    private void handleRemoveFamilyMember(String data) throws IOException {
        synchronized (userManager) {
            FamilyMemberData familyMemberData = gson.fromJson(data, FamilyMemberData.class);
            User user = userManager.getUserById(familyMemberData.getUserId());
            User familyMember = userManager.getUserById(familyMemberData.getFamilyMemberId());
            user.removeFamilyMember(familyMember);
            String responseJson = gson.toJson(new Response("success", "Family member removed successfully"));
            output.writeUTF(responseJson);
        }
    }
}
