package Main;

import DAO.UserManager;

public class LoginSystem {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        // Add users
        userManager.addUser("user1", "surname1", "admin");
        userManager.addUser("user2", "surname2", "Student");
        String enteredUsername = "user1"; // Example
        String enteredPassword = "surname1";

        if (userManager.authenticateUser(enteredUsername, enteredPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}