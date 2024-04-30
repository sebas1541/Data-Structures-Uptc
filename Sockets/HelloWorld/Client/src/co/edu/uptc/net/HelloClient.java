package co.edu.uptc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloClient {

    private final String HOST = "localhost";
    private final int PORT = 1234;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    public HelloClient() throws IOException {
        socket = new Socket(HOST, PORT);
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    public void start() throws IOException {
        for(int i = 0; i < 5; i++){
            output.writeUTF("Message " + (i+1));
        }
        System.out.println(input.readUTF());
        System.out.println(input.readUTF());
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        new HelloClient().start();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of messages: ");
        int numMessages = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        HelloClient client = new HelloClient();
        client.start(numMessages);
    }

    public void start(int numMessages) throws IOException {
        for (int i = 0; i < numMessages; i++) {
            System.out.print("Enter message " + (i + 1) + ": ");
            String message = scanner.nextLine();
            output.writeUTF(message);
        }
        System.out.println(input.readUTF());
        System.out.println(input.readUTF());
        socket.close();
    }
}
