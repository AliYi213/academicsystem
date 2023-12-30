package Controllers;

import DAO.CourseDAO;
import Models.Course;

public class CourseController {
    private final CourseDAO courseDAO;

    public CourseController() {
        this.courseDAO = new CourseDAO();
    }

    public boolean createCourse(String courseName) {
        Course course = new Course(0, courseName);
        return courseDAO.createCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }
}
