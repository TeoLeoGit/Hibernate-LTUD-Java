package pojo;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private int Id;
    private String teacherName;
    private String classroom;
    private String dayOfWeek;
    private int shift;
    private Semester semester;
    private Subject subject;
    private Set<Coursesregistration> coursesregistrations = new HashSet<Coursesregistration>(0);
    private int maxAttendant;
    private int Attendant;

    public int getAttendant() {
        return Attendant;
    }

    public void setAttendant(int attendant) {
        Attendant = attendant;
    }

    public int getMaxAttendant() {
        return maxAttendant;
    }

    public void setMaxAttendant(int maxAttendant) {
        this.maxAttendant = maxAttendant;
    }

    public Set<Coursesregistration> getCoursesregistrations() {
        return coursesregistrations;
    }

    public void setCoursesregistrations(Set<Coursesregistration> coursesregistrations) {
        this.coursesregistrations = coursesregistrations;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroom() {
        return this.classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
