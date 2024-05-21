package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.Transaction;
import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ListTransactionsHandler implements RequestHandler {
    @Override
    public void handle(String userId, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
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
}
