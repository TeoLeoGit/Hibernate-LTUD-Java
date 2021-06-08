package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


public class Coursesregistration {
    private Student student;
    private Course course;
    private Date registerdate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

}
