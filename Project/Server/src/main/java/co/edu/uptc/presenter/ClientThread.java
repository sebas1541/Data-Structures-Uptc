package co.edu.uptc.presenter;

import co.edu.uptc.model.UserManager;
import co.edu.uptc.utils.LocalDateTimeAdapter;
import co.edu.uptc.net.Request;
import co.edu.uptc.net.Response;
import co.edu.uptc.presenter.handler.BudgetHandler;
import co.edu.uptc.presenter.handler.FamilyGroupHandler;
import co.edu.uptc.presenter.handler.TransactionHandler;
import co.edu.uptc.presenter.handler.UserHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static UserManager userManager;
    private Gson gson;
    private UserHandler userHandler;
    private TransactionHandler transactionHandler;
    private BudgetHandler budgetHandler;
    private FamilyGroupHandler familyGroupHandler;

    public ClientThread(Socket socket, UserManager userManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        ClientThread.userManager = userManager;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        this.userHandler = new UserHandler(userManager, gson);
        this.transactionHandler = new TransactionHandler(userManager, gson);
        this.budgetHandler = new BudgetHandler(userManager, gson);
        this.familyGroupHandler = new FamilyGroupHandler(userManager, gson);
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
                        userHandler.login(request.getData(), output);
                        break;
                    case "register":
                        userHandler.register(request.getData(), output);
                        break;
                    case "addTransaction":
                        transactionHandler.addTransaction(request.getData(), output);
                        break;
                    case "editTransaction":
                        transactionHandler.editTransaction(request.getData(), output);
                        break;
                    case "viewTransactions":
                        transactionHandler.viewTransactions(request.getData(), output);
                        break;
                    case "listTransactions":
                        transactionHandler.listTransactions(request.getData(), output);
                        break;
                    case "deleteTransaction":
                        transactionHandler.deleteTransaction(request.getData(), output);
                        break;
                    case "addBudget":
                        budgetHandler.addBudget(request.getData(), output);
                        break;
                    case "editBudget":
                        budgetHandler.editBudget(request.getData(), output);
                        break;
                    case "deleteBudget":
                        budgetHandler.deleteBudget(request.getData(), output);
                        break;
                    case "viewBudget":
                        budgetHandler.viewBudget(request.getData(), output);
                        break;
                    case "addMember":
                        familyGroupHandler.addMember(request.getData(), output);
                        break;
                    case "viewMembers":
                        familyGroupHandler.viewMembers(request.getData(), output);
                        break;
                    case "viewFamilyMemberTransactions":
                        transactionHandler.viewFamilyMemberTransactions(request.getData(), output);
                        break;
                    case "disconnect":
                        running = false;
                        break;
                    default:
                        String responseJson = gson.toJson(new Response("error", "Invalid action"));
                        output.writeUTF(responseJson);
                        break;
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

}
