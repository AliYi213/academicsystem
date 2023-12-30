package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DatabaseConnector;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class AcademicSystem {
    public Grade getStudentGrade(int studentId, int subjectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Grade grade = null;

        try{
            connection = DatabaseConnector.connect();
            //retrieve grade
            String query = "SELECT g.score FROM grades g " +
                    "JOIN Students s ON g.student_id = s.student_id " +
                    "WHERE s.student_id = ? AND s.subject_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int score = resultSet.getInt("grade");
                grade = new Grade(studentId, subjectId, score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datautil.close(resultSet);
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
        return grade;
    }
    public void enterGrade(int studentId, int subjectId, Grade grade) {
        // Check if the grade already exists for the student and subject
        if (doesGradeExist(studentId, subjectId)) {
            System.out.println("Grade already exists. Use editGrade to modify.");
        } else {
            // Save the new grade for the student and subject to the database
            insertNewGrade(studentId, subjectId, grade);
            System.out.println("Grade entered successfully.");
        }
    }

    // Insert a new grade into the database
    private void insertNewGrade(int studentId, int subjectId, Grade grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to insert a new grade
            String query = "INSERT INTO grades (student_id, subject_id, score) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            preparedStatement.setInt(3, grade.getScore());

            // Execute the query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }

    // Edit an existing grade for a student and subject
    public void editGrade(int studentId, int subjectId, Grade newGrade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to update the grade
            String query = "UPDATE grades SET score = ? WHERE student_id = ? AND subject_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newGrade.getScore());
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, subjectId);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Grade updated successfully.");
            } else {
                System.out.println("No existing grade found to edit.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a final block
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
    // Method to view grades for a specific student
    public List<Grade> viewOwnGrades(int studentId) {
        List<Grade> grades = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to retrieve grades for the specified student
            String query = "SELECT g.subject_id, g.score " +
                    "FROM Students s " +
                    "INNER JOIN grades g ON s.student_id = g.student_id " +
                    "WHERE s.student_id = ?";;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                int score = resultSet.getInt("score");

                // Create a Grade object and add it to the list
                Grade grade = new Grade(studentId, subjectId, score);
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(resultSet);
            datautil.close(preparedStatement);
            datautil.close(connection);
        }

        return grades;
    }
    public class datautil {
        public static void close(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void close(PreparedStatement preparedStatement) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // Close Connection
        public static void close(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private boolean doesGradeExist(int studentId, int subjectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean gradeExists = false;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to check if the grade exists
            String query = "SELECT * FROM grades WHERE student_id = ? AND subject_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result exists
            if (resultSet.next()) {
                gradeExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(resultSet);
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
        return gradeExists;
    }
    // Method to create a student group
    public void createStudentGroup(Group group) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to insert a new student group
            String query = "INSERT INTO StudentGroups (group_id, group_name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, group.getGroupId());
            preparedStatement.setString(2, group.getGroupName());

            // Execute the query
            preparedStatement.executeUpdate();
            System.out.println("Student group created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
    public void deleteCourse(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to delete a course
            String query = "DELETE FROM Courses WHERE course_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);

            // Execute the query
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("No course found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a final block
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }

    public void deleteStudentGroup(int groupId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to delete a student group
            String query = "DELETE FROM StudentGroups WHERE group_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, groupId);

            // Execute the query
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student group deleted successfully.");
            } else {
                System.out.println("No student group found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
    public void createStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to insert a new student
            String query = "INSERT INTO Students (student_id, student_name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());

            // Execute the query
            preparedStatement.executeUpdate();
            System.out.println("Student created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
    // Method to assign a lecturer to a subject
    public boolean assignSubject(int lecturerId, int subjectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to assign a lecturer to a subject
            String query = "UPDATE Subjects SET lecturer_id = ? WHERE subject_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lecturerId);
            preparedStatement.setInt(2, subjectId);

            // Execute the query
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Lecturer assigned to subject successfully.");
            } else {
                System.out.println("Subject or lecturer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
        return false;
    }
    public boolean assignGroup(int studentId, int groupId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to assign a student to a group
            String query = "UPDATE Students SET group_id = ? WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, studentId);

            // Execute the query
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student assigned to group successfully.");
            } else {
                System.out.println("Student or group not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
        return false;
    }
    public void createCourse(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to insert a new course
            String query = "INSERT INTO Courses (course_id, course_name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());

            // Execute the query
            preparedStatement.executeUpdate();
            System.out.println("Course created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
    // Method to delete a student
    public void deleteStudent(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DatabaseConnector.connect();

            // SQL query to delete a student by ID
            String query = "DELETE FROM Students WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datautil.close(preparedStatement);
            datautil.close(connection);
        }
    }
}
