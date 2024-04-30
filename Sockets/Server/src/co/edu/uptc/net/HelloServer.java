package co.edu.uptc.net;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {

    private final int PORT = 1234;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    public HelloServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void start() throws IOException {
        while (true){
            socket = serverSocket.accept();
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
            output.writeUTF("conexion aceptada");
            System.out.println("cliente conectado");
            System.out.println("datosrecibidos");

            for(int i = 0; i < 5; i++){
                System.out.println(input.readUTF());
            }
            System.out.println("fin de la transmision");
            output.writeUTF("datos recibidos exitosamente");
            
        }
        socket.close();


    }
}
