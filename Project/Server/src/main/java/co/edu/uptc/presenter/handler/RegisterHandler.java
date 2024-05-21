package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.Response;
import co.edu.uptc.net.UserData;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RegisterHandler implements RequestHandler {
    @Override
    public void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
        synchronized (userManager) {
            UserData userData = gson.fromJson(data, UserData.class);
            if (userManager.getUserByUsername(userData.getUsername()) == null) {
                try {
                    User user = new User(userData.getUsername(), userData.getPassword(), userData.getEmail());
                    userManager.addUser(user);
                    String responseJson = gson.toJson(new Response("success", "User registered successfully"));
                    output.writeUTF(responseJson);
                } catch (NoSuchAlgorithmException e) {
                    String responseJson = gson.toJson(new Response("error", "Internal server error"));
                    output.writeUTF(responseJson);
                }
            } else {
                String responseJson = gson.toJson(new Response("error", "Username already exists"));
                output.writeUTF(responseJson);
            }
        }
    }
}
