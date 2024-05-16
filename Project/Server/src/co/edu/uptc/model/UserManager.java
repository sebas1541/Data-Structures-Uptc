package co.edu.uptc.model;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    // Method to add a user
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    // Method to get a user by ID
    public User getUserById(String userId) {
        return users.get(userId);
    }

    // Method to remove a user by ID
    public void removeUser(String userId) {
        users.remove(userId);
    }
}
