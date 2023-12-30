package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Lecturer;

public class LecturerDAO {
    private final String DB_URL = "jdbc:sqlite:database.db";

    public boolean createLecturer(Lecturer lecturer) {
        String query = "INSERT INTO lecturers (lecturer_name, department) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, lecturer.getLecturerName());
            preparedStatement.setInt(2, lecturer.getLecturerId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if insertion is successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteLecturer(int lecturerId) {
        String query = "DELETE FROM lecturers WHERE lecturer_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, lecturerId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String query = "SELECT * FROM lecturers";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int lecturerId = resultSet.getInt("lecturer_id");
                String lecturerName = resultSet.getString("lecturer_name");
                String department = resultSet.getString("department");

                Lecturer lecturer = new Lecturer(lecturerId, lecturerName);
                lecturers.add(lecturer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lecturers;
    }
}

