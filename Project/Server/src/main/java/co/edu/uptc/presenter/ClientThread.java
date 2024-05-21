package co.edu.uptc.presenter;

import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.LocalDateTimeAdapter;
import co.edu.uptc.presenter.handler.*;
import co.edu.uptc.net.Request;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private static UserManager userManager;
    private Gson gson;
    private Map<String, RequestHandler> handlers;

    public ClientThread(Socket socket, UserManager userManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        ClientThread.userManager = userManager;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        this.handlers = new HashMap<>();
        initializeHandlers();
    }

    private void initializeHandlers() {
        handlers.put("login", new LoginHandler());
        handlers.put("register", new RegisterHandler());
        handlers.put("addTransaction", new AddTransactionHandler());
        handlers.put("editTransaction", new EditTransactionHandler());
        handlers.put("viewTransactions", new ViewTransactionsHandler());
        handlers.put("listTransactions", new ListTransactionsHandler());
        handlers.put("deleteTransaction", new DeleteTransactionHandler());
    }

    @Override
    public void run() {
        try {
            boolean running = true;
            while (running) {
                String requestJson = input.readUTF();
                System.out.println("Received request: " + requestJson); // Logging JSON
                Request request = gson.fromJson(requestJson, Request.class);
                if (handlers.containsKey(request.getAction())) {
                    handlers.get(request.getAction()).handle(request.getData(), output, userManager, gson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "Invalid action"));
                    output.writeUTF(responseJson);
                }

                if ("disconnect".equals(request.getAction())) {
                    running = false;
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
