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

public class EditTransactionHandler implements RequestHandler {
    @Override
    public void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
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
}
