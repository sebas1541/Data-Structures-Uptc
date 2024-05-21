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

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public User getUserByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void removeUser(String userId) {
        users.remove(userId);
    }
}

