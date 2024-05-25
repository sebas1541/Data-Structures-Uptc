package co.edu.uptc.presenter;

import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Connection;
import co.edu.uptc.persistence.FileManager;

import java.io.IOException;
import java.net.Socket;

public class ServerPresenter {
    private Connection connection;
    private UserManager userManager;
    private static final String DATA_FILE_PATH = "data/server.dat";

    public ServerPresenter() throws IOException {
        connection = new Connection();
        userManager = FileManager.loadObject(DATA_FILE_PATH);
        if (userManager == null) {
            userManager = new UserManager();
        }
        runServer();
    }

    private void runServer() {
        try {
            System.out.println("Server is running and listening for client connections...");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                FileManager.saveObject(userManager, DATA_FILE_PATH);
                System.out.println("Server state saved.");
            }));
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
                FileManager.saveObject(userManager, DATA_FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
