package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.LoginData;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginHandler implements RequestHandler {
    @Override
    public void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException {
        synchronized (userManager) {
            LoginData loginData = gson.fromJson(data, LoginData.class);
            User user = userManager.getUserByUsername(loginData.getUsername());
            try {
                if (user != null && user.verifyPassword(loginData.getPassword())) {
                    String responseJson = gson.toJson(new Response("success", "Login successful"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "Invalid username or password"));
                    output.writeUTF(responseJson);
                }
            } catch (NoSuchAlgorithmException e) {
                String responseJson = gson.toJson(new Response("error", "Internal server error"));
                output.writeUTF(responseJson);
            }
        }
    }
}
