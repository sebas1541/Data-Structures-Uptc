package co.edu.uptc.model;

import co.edu.uptc.net.Connection;

import java.io.IOException;
import java.net.Socket;

public class Model {



    private Connection connection;

    public Model() throws IOException {
        connection = new Connection();
    }

    public Socket acceptConnection() throws IOException {
        connection.connect();
        return connection.getSocket();
    }

    public String sendAcceptanceMessage() throws IOException {
        connection.getOutput().writeUTF("Conexión exitosa");
        return "Conexión exitosa";
    }

    public String receiveData() throws IOException {
        StringBuilder data = new StringBuilder();

        for(int i = 0; i < 5; i++){
            data.append(connection.getInput().readUTF()).append("\n");
        }
        return data.toString();
    }

    public String endTransmission() throws IOException {
        connection.getOutput().writeUTF("Datos Recibidos Exitosamente");
        connection.getSocket().close();
        return "Fin de la transmisión";
    }
}