package Models;

public class Lecturer extends User{
    private int lecturerId;
    private String lecturerName;

    public Lecturer(int lecturerId, String lecturerName) {
        super(lecturerId, lecturerName);
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
