package src.model;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static Map<String, User> users = new HashMap<>();

    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        User newUser = new User(username, password);
        users.put(username, newUser);
        return true;
    }

    public static boolean authenticateUser(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }

    public static User getUser(String username) {
        return users.get(username);
    }
}
