package co.edu.uptc.presenter;

import co.edu.uptc.model.Transaction;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static UserManager userManager;
    private Gson gson;

    public ClientThread(Socket socket, UserManager userManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        ClientThread.userManager = userManager;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
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
                    case "login":
                        handleLogin(request.getData());
                        break;
                    case "register":
                        handleRegister(request.getData());
                        break;
                    case "addTransaction":
                        handleAddTransaction(request.getData());
                        break;
                    case "editTransaction":
                        handleEditTransaction(request.getData());
                        break;
                    case "viewTransactions":
                        handleViewTransactions(request.getData());
                        break;
                    case "listTransactions":
                        handleListTransactions(request.getData());
                        break;
                    case "deleteTransaction":
                        handleDeleteTransaction(request.getData());
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

    private void handleListTransactions(String userId) throws IOException {
        synchronized (userManager) {
            User user = userManager.getUserByUsername(userId);
            if (user != null) {
                List<Transaction> transactions = user.getTransactions().inOrder();
                StringBuilder transactionList = new StringBuilder();
                int counter = 1;
                for (Transaction transaction : transactions) {
                    transactionList.append(counter++)
                            .append(": ")
                            .append(transaction.toString())
                            .append("\n");
                }
                String responseJson = gson.toJson(new Response("success", transactionList.toString()));
                output.writeUTF(responseJson);
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleLogin(String data) throws IOException {
        synchronized (userManager) {
            LoginData loginData = gson.fromJson(data, LoginData.class);
            User user = userManager.getUserByUsername(loginData.getUsername());
            try {
                if (user != null && user.verifyPassword(loginData.getPassword())) {
                    String responseJson = gson.toJson(new Response("success", "Login successful"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "Invalid username or password"));
                    output.writeUTF(responseJson);
                }
            } catch (NoSuchAlgorithmException e) {
                String responseJson = gson.toJson(new Response("error", "Internal server error"));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleRegister(String data) throws IOException {
        synchronized (userManager) {
            UserData userData = gson.fromJson(data, UserData.class);
            if (userManager.getUserByUsername(userData.getUsername()) == null) {
                try {
                    User user = new User(userData.getUsername(), userData.getPassword(), userData.getEmail());
                    userManager.addUser(user);
                    String responseJson = gson.toJson(new Response("success", "User registered successfully"));
                    output.writeUTF(responseJson);
                } catch (NoSuchAlgorithmException e) {
                    String responseJson = gson.toJson(new Response("error", "Internal server error"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "Username already exists"));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleAddTransaction(String data) throws IOException {
        synchronized (userManager) {
            TransactionData transactionData = gson.fromJson(data, TransactionData.class);
            try {
                User user = userManager.getUserByUsername(transactionData.getUserId());
                if (user != null) {
                    String transactionId = "TXN-" + System.currentTimeMillis(); // Generate a unique transaction ID
                    Transaction transaction = new Transaction(
                            transactionId,
                            transactionData.getAmount(),
                            LocalDateTime.parse(transactionData.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            transactionData.getCategory(),
                            transactionData.getDescription(),
                            transactionData.getType()
                    );
                    System.out.println("Adding transaction: " + transaction); // Log the transaction being added
                    user.addTransaction(transaction);
                    String responseJson = gson.toJson(new Response("success", "Transaction added successfully"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "User not found"));
                    output.writeUTF(responseJson);
                }
            } catch (Exception e) {
                String responseJson = gson.toJson(new Response("error", e.getMessage()));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleEditTransaction(String data) throws IOException {
        synchronized (userManager) {
            TransactionData transactionData = gson.fromJson(data, TransactionData.class);
            User user = userManager.getUserByUsername(transactionData.getUserId());
            if (user != null) {
                System.out.println("Editing transaction with ID: " + transactionData.getTransactionId()); // Log the transaction ID being searched
                Transaction searchTransaction = new Transaction(transactionData.getTransactionId(), 0, null, null, null, null);
                Transaction transaction = user.getTransactions().search(searchTransaction);
                if (transaction != null) {
                    System.out.println("Transaction found: " + transaction); // Log the transaction found
                    // Ensure transaction dateTime is not null before comparison
                    if (transactionData.getDateTime() != null) {
                        transaction.setDateTime(LocalDateTime.parse(transactionData.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    } else if (transaction.getDateTime() == null) {
                        transaction.setDateTime(LocalDateTime.now());
                    }
                    // Update the transaction
                    transaction.setAmount(transactionData.getAmount());
                    transaction.setDescription(transactionData.getDescription());
                    transaction.setType(transactionData.getType());
                    transaction.setCategory(transactionData.getCategory());

                    String responseJson = gson.toJson(new Response("success", "Transaction updated successfully"));
                    output.writeUTF(responseJson);
                } else {
                    System.out.println("Transaction not found: " + searchTransaction); // Log the search transaction details
                    String responseJson = gson.toJson(new Response("error", "Transaction not found"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleDeleteTransaction(String data) throws IOException {
        synchronized (userManager) {
            String[] parts = data.split(",");
            String userId = parts[0];
            String transactionId = parts[1];
            User user = userManager.getUserByUsername(userId);
            if (user != null) {
                Transaction transaction = user.getTransactions().search(new Transaction(transactionId, 0, null, null, null, null));
                if (transaction != null) {
                    try {
                        user.removeTransaction(transaction);
                        String responseJson = gson.toJson(new Response("success", "Transaction deleted successfully"));
                        output.writeUTF(responseJson);
                    } catch (Exception e) {
                        String responseJson = gson.toJson(new Response("error", e.getMessage()));
                        output.writeUTF(responseJson);
                    }
                } else {
                    String responseJson = gson.toJson(new Response("error", "Transaction not found"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }

    private void handleViewTransactions(String data) throws IOException {
        synchronized (userManager) {
            User user = userManager.getUserByUsername(data);
            if (user != null) {
                List<Transaction> transactions = user.getTransactions().inOrder();
                String responseJson = gson.toJson(new Response("success", gson.toJson(transactions)));
                output.writeUTF(responseJson);
            } else {
                String responseJson = gson.toJson(new Response("error", "User not found"));
                output.writeUTF(responseJson);
            }
        }
    }
}
