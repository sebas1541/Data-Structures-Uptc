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

public class AddTransactionHandler implements RequestHandler {
    @Override
    public void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
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
}
