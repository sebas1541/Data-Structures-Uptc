package co.edu.uptc.presenter;

import co.edu.uptc.models.Employees;
import co.edu.uptc.net.Connection;

import java.io.IOException;
import java.net.Socket;

public class ServerPresenter {
    private Connection connection;
    private Employees employees;

    public ServerPresenter() throws IOException {
        connection = new Connection();
        employees = new Employees();
        runServer();
    }

    private void runServer() {
        try {
            while (true) {
                Socket clientSocket = connection.getServerSocket().accept();
                ClientThread clientThread = new ClientThread(clientSocket, employees);
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
