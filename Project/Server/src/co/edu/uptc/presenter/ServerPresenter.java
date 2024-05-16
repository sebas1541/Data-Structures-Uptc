package co.edu.uptc.presenter;

import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Connection;

import java.io.IOException;
import java.net.Socket;

public class ServerPresenter {
    private Connection connection;
    private UserManager userManager;

    public ServerPresenter() throws IOException {
        connection = new Connection();
        userManager = new UserManager();
        runServer();
    }

    private void runServer() {
        try {
            System.out.println("Server is running and listening for client connections...");
            while (true) {
                Socket clientSocket = connection.getServerSocket().accept();
                ClientThread clientThread = new ClientThread(clientSocket, userManager);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.getServerSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerPresenter();
    }
}
