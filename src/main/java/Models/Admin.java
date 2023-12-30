package Models;

public class Admin extends User {
    private AcademicSystem academicSystem;
    private String adminRole;


    public Admin(String adminRole) {
        super(adminRole);
        this.adminRole = adminRole;
    }
    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public void createStudentGroup(Group group) {
        academicSystem.createStudentGroup(group);
    }

    public void deleteStudentGroup(int groupId) {
        academicSystem.deleteStudentGroup(groupId);
    }

    public void createCourse(Course course) {
        academicSystem.createCourse(course);
    }

    public void deleteCourse(int courseId) {
        academicSystem.deleteCourse(courseId);
    }

    public void createStudent(Student student) {
        academicSystem.createStudent(student);
    }

    public void deleteStudent(int studentId) {
        academicSystem.deleteStudent(studentId);
    }

    public void assignSubject(int lecturerId, int subjectId) {
        academicSystem.assignSubject(lecturerId, subjectId);
    }

    public void assignGroup(int studentId, int groupId) {
        academicSystem.assignGroup(studentId, groupId);
    }
}

