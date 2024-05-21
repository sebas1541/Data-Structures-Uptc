package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.Transaction;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;

public class DeleteTransactionHandler implements RequestHandler {
    @Override
    public void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
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
}
