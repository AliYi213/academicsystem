package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.Course;
public class CourseDAO {
    private final String DB_URL = "jdbc:sqlite:database.db";

    public boolean createCourse(Course course) {
        String query = "INSERT INTO courses (course_name, course_code) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCourseId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, courseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");

                Course course = new Course(courseId, courseName);
                courses.add(course);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courses;
    }
}

