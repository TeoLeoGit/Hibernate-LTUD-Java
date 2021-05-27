package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@IdClass(CoursesregistrationPK.class)
public class Coursesregistration {
    private int studentid;
    private int courseid;
    private Date registerdate;

    @Id
    @Column(name = "studentid", nullable = false)
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Id
    @Column(name = "courseid", nullable = false)
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "registerdate", nullable = false)
    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coursesregistration that = (Coursesregistration) o;
        return Objects.equals(studentid, that.studentid) && Objects.equals(courseid, that.courseid) && Objects.equals(registerdate, that.registerdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentid, courseid, registerdate);
    }
}
