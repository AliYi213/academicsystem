package Models;
public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int studentId, String name, String subjectid) {
    }

    public User(String adminRole) {
    }

    public User(int lecturerId, String lecturerName) {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
