package co.edu.uptc.net;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloClient {
    public final static String HOST = "localhost";
    public final static int PORT = 1234;
    private Socket socket;

    private DataInputStream input;
    private DataOutputStream output;

    public HelloClient() throws IOException {
        this.socket = new Socket(HOST, PORT);
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }



    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(input.readUTF());

        for(int i = 0; i < 5; i++){
            System.out.print("Enter message " + (i+1) + ": ");
            String message = scanner.nextLine();
            output.writeUTF(message);
            System.out.print("Do you want to send another message? (yes/no): ");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("no")) {
                break;
            }
        }
        System.out.println("Response from the server: " + input.readUTF());
        socket.close();
    }


}
