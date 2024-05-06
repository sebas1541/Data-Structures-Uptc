package co.edu.uptc.presenter;

import co.edu.uptc.models.Calculator;
import co.edu.uptc.net.Connection;
import java.io.IOException;

public class ServerPresenter {
    private Connection connection;
    private Calculator calculator;

    public ServerPresenter() throws IOException {
        connection = new Connection();
        calculator = new Calculator();
        runServer();
    }

    private void runServer() {
        try {
            connection.connect();
            boolean running = true;
            while (running) {
                String command = connection.getInput().readUTF();
                switch (command) {
                    case "1":
                        double a1 = connection.getInput().readDouble();
                        double b1 = connection.getInput().readDouble();
                        double resultAdd = calculator.add(a1, b1);
                        connection.getOutput().writeDouble(resultAdd);
                        break;
                    case "2":
                        double a2 = connection.getInput().readDouble();
                        double b2 = connection.getInput().readDouble();
                        double resultSub = calculator.subtract(a2, b2);
                        connection.getOutput().writeDouble(resultSub);
                        break;
                    case "3":
                        double a3 = connection.getInput().readDouble();
                        double b3 = connection.getInput().readDouble();
                        double resultMul = calculator.multiply(a3, b3);
                        connection.getOutput().writeDouble(resultMul);
                        break;
                    case "4":
                        double a4 = connection.getInput().readDouble();
                        double b4 = connection.getInput().readDouble();
                        try {
                            double resultDiv = calculator.divide(a4, b4);
                            connection.getOutput().writeDouble(resultDiv);
                        } catch (IllegalArgumentException e) {
                            connection.getOutput().writeUTF(e.getMessage());
                        }
                        break;
                    case "5":
                        running = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.getSocket().close();
                    connection.getServerSocket().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
