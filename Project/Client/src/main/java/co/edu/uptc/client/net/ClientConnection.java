package co.edu.uptc.client.net;

import com.google.gson.Gson;


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
    private Gson gson;

    public ClientConnection(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.gson = new Gson();
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    public void sendRequest(Request request) throws IOException {
        String requestJson = gson.toJson(request);
        System.out.println("Sending request: " + requestJson); // Logging
        output.writeUTF(requestJson);
    }

    public Response receiveResponse() throws IOException {
        String responseJson = input.readUTF();
        System.out.println("Received response: " + responseJson); // Logging
        return gson.fromJson(responseJson, Response.class);
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (output != null) output.close();
        if (input != null) input.close();
    }
}
