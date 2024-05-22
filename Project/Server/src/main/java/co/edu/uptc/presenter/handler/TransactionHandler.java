package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.Transaction;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Response;
import co.edu.uptc.net.TransactionData;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionHandler {
    private UserManager userManager;
    private Gson gson;

    public TransactionHandler(UserManager userManager, Gson gson) {
        this.userManager = userManager;
        this.gson = gson;
    }

    public void addTransaction(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            TransactionData transactionData = gson.fromJson(data, TransactionData.class);
            try {
                User user = userManager.getUserByUsername(transactionData.getUserId());
                if (user != null) {
                    String transactionId = "TXN-" + System.currentTimeMillis(); // Generate a unique transaction ID
                    Transaction transaction = new Transaction(
                            transactionId,
                            transactionData.getUserId(), // Pass userId here
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

    public void deleteTransaction(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            String[] parts = data.split(",");
            String userId = parts[0];
            String transactionId = parts[1];
            User user = userManager.getUserByUsername(userId);
            if (user != null) {
                Transaction transaction = user.getTransactions().search(new Transaction(transactionId));
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

    public void editTransaction(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            TransactionData transactionData = gson.fromJson(data, TransactionData.class);
            User user = userManager.getUserByUsername(transactionData.getUserId());
            if (user != null) {
                System.out.println("Editing transaction with ID: " + transactionData.getTransactionId()); // Log the transaction ID being searched
                Transaction searchTransaction = new Transaction(transactionData.getTransactionId());
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

    public void viewTransactions(String data, DataOutputStream output) throws IOException {
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

    public void listTransactions(String data, DataOutputStream output) throws IOException {
        synchronized (userManager) {
            User user = userManager.getUserByUsername(data);
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
}
