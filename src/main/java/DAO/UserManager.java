package DAO;
import Models.User;
import java.util.HashMap;
import java.util.Map;
public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void addUser(String username, String password, String role) {
        users.put(username, new User(username, password, role));
    }

    public boolean authenticateUser(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            return user.getPassword().equals(password); // Check if passwords match
        }
        return false;
    }
}
