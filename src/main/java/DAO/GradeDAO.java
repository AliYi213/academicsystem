package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.Grade;

public class GradeDAO {
    private final String DB_URL = "jdbc:sqlite:database.db";

    public boolean insertGrade(int studentId, int subjectId, Grade grade) {
        String query = "INSERT INTO grades (student_id, subject_id, grade) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            preparedStatement.setInt(3, grade.getScore());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateGrade(int studentId, int subjectId, Grade newGrade) {
        String query = "UPDATE grades SET grade = ? WHERE student_id = ? AND subject_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, newGrade.getScore());
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, subjectId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteGrade(int studentId, int subjectId) {
        String query = "DELETE FROM grades WHERE student_id = ? AND subject_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM grades";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int subjectId = resultSet.getInt("subject_id");
                int gradeValue = resultSet.getInt("grade");

                Grade grade = new Grade(studentId, subjectId, gradeValue);
                grades.add(grade);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return grades;
    }
    public Grade getStudentGrade(int studentId, int subjectId) {
        String query = "SELECT * FROM grades WHERE student_id = ? AND subject_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int score = resultSet.getInt("score");
                return new Grade(studentId, subjectId, score);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

