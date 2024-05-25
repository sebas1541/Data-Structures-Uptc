package co.edu.uptc.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Serializable {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public void updateUser(User user) {
        users.put(user.getUsername(), user); // Ensure this updates the user data
    }


}
