package Controllers;
import org.mindrot.jbcrypt.BCrypt;
import DAO.UserDAO;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String username, String password, String role) {
        if (userDAO.isUsernameUnique(username)) {
            String hashedPassword = hashPassword(password);
            return userDAO.insertUser(username, hashedPassword, role);
        } else {
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    public boolean updatePassword(String username, String newPassword) {
        String hashedPassword = hashPassword(newPassword);
        return userDAO.updateUserPassword(username, hashedPassword);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}

