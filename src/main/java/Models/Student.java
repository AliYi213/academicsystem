package Models;

public class Student extends User{
    private int studentId;
    private int subjectid;
    private String name;

    public Student(int studentId,String name,int subjectid ) {
        super(studentId,name, String.valueOf(subjectid));
    }

    public String getName() {
        return name;
    }
    public int getStudentId() {
        return studentId;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMajor(String major) {
        this.subjectid = subjectid;
    }
}
