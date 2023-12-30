package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.UserManager;
import database.DatabaseConnector;

import static database.DatabaseConnector.connect;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnector.connect();

        if (connection != null) {
            System.out.println("Connection to database established successfully!");

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
                 while (resultSet.next()) {
                    System.out.println("Student ID: " + ((ResultSet) resultSet).getInt("student_id")
                            + ", Name: " + resultSet.getString("first_name") + " " + resultSet.getString("last_name")
                            + ", Group ID: " + resultSet.getInt("group_id"));
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error executing SQL query: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to establish connection to the database.");
        }
    }

}