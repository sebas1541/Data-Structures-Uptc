package co.edu.uptc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    private final String serverAddress;
    private final int serverPort;

    public ClientConnection(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    public void sendOperation(String operation) throws IOException {
        output.writeUTF(operation);
    }

    public void sendNumber(double number) throws IOException {
        output.writeDouble(number);
    }

    public double receiveResult() throws IOException {
        return input.readDouble();
    }

    public String receiveMessage() throws IOException {
        return input.readUTF();
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (output != null) output.close();
        if (input != null) input.close();
    }
}
