package Controllers;
import DAO.CourseDAO;
import DAO.LecturerDAO;
import DAO.StudentDAO;
import Models.AcademicSystem;
import Models.Student;
import Models.Course;
import Models.Group;
import DAO.GroupDAO;

public class AdminController {
    private final CourseDAO courseDAO;
    private final GroupDAO groupDAO;
    private final StudentDAO studentDAO;
    private final LecturerDAO lecturerDAO;
    private final AcademicSystem academicSystem;

    public AdminController() {
        this.courseDAO = new CourseDAO();
        this.groupDAO = new GroupDAO();
        this.studentDAO = new StudentDAO();
        this.lecturerDAO = new LecturerDAO();
        this.academicSystem = new AcademicSystem();
    }

    public boolean createCourse(Course course) {
        return courseDAO.createCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public boolean createStudentGroup(Group group) {
        return groupDAO.createGroup(group);
    }

    public boolean deleteStudentGroup(int groupId) {
        return groupDAO.deleteGroup(groupId);
    }

    public boolean createStudent(Student student) {
        return studentDAO.createStudent(student);
    }

    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    public boolean assignLecturerToSubject(int lecturerId, int subjectId) {
        return academicSystem.assignSubject(lecturerId, subjectId);
    }

    public boolean assignStudentToGroup(int studentId, int groupId) {
        return academicSystem.assignGroup(studentId, groupId);
    }
}
