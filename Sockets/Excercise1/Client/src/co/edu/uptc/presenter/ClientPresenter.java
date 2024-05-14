package co.edu.uptc.presenter;

import co.edu.uptc.views.View;
import co.edu.uptc.net.ClientConnection;
import java.io.IOException;

public class ClientPresenter {
    private ClientConnection connection;
    private View view;

    public ClientPresenter() {
        view = new View();
        connection = new ClientConnection("localhost", 1234);
        try {
            connection.connect();
            runClient();
        } catch (IOException e) {
            view.showMessage("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    private void runClient() {
        boolean running = true;
        while (running) {
            String operation = view.readString("Introduzca operación (1. Suma, 2. Resta, 3. Multiplicación, 4. División, 5. Salir):");
            try {
                connection.sendOperation(operation);
                if ("5".equals(operation)) {
                    running = false;
                } else {
                    double num1 = view.readInt("Ingrese el primer número:");
                    double num2 = view.readInt("Ingrese el segundo número:");
                    connection.sendNumber(num1);
                    connection.sendNumber(num2);

                    if ("4".equals(operation) && num2 == 0) {
                        String message = connection.receiveMessage(); 
                        view.showMessage(message);
                    } else {
                        double result = connection.receiveResult();
                        view.showMessage("Resultado: " + result);
                    }
                }
            } catch (IOException e) {
                view.showMessage("Error al comunicarse con el servidor: " + e.getMessage());
                running = false;
            }
        }
        closeConnection();
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (IOException e) {
            view.showMessage("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
