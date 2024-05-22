package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.LoginData;
import co.edu.uptc.net.Response;
import co.edu.uptc.net.UserData;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserHandler {
    private UserManager userManager;
    private Gson gson;

    public UserHandler(UserManager userManager, Gson gson) {
        this.userManager = userManager;
        this.gson = gson;
    }

    public void register(String data, DataOutputStream output) throws IOException {
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

    public void login(String data, DataOutputStream output) throws IOException {
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
