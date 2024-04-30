package co.edu.uptc.presenter;

import co.edu.uptc.model.Model;

import java.io.IOException;

public class HelloServer {

    private Model model;

    public HelloServer() throws IOException {
        model = new Model();
    }

    public void start() throws IOException {
            model.acceptConnection();
            String acceptanceMessage = model.sendAcceptanceMessage();
            System.out.println(model.receiveData());
            System.out.println(model.endTransmission());

    }
}