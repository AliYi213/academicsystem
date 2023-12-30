package Controllers;
import java.util.List;
import Models.Grade;
import DAO.GradeDAO;

public class GradeController {
    private final GradeDAO gradeDAO;

    public GradeController() {
        this.gradeDAO = new GradeDAO();
    }

    public boolean enterGrade(int studentId, int subjectId, Grade grade) {

        if (gradeDAO.getStudentGrade(studentId, subjectId) != null) {
            System.out.println("Grade already exists. Use editGrade to modify.");
            return false;
        } else {
            return gradeDAO.insertGrade(studentId, subjectId, grade);
        }
    }

    public boolean editGrade(int studentId, int subjectId, Grade newGrade) {
        if (gradeDAO.getStudentGrade(studentId, subjectId) != null) {
            return gradeDAO.updateGrade(studentId, subjectId, newGrade);
        } else {
            System.out.println("No existing grade found to edit.");
            return false;
        }
    }

    public boolean deleteGrade(int studentId, int subjectId) {
        return gradeDAO.deleteGrade(studentId, subjectId);
    }
    public List<Grade> getAllGrades() {
        return gradeDAO.getAllGrades();
    }
}

