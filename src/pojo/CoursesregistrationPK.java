package pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CoursesregistrationPK implements Serializable {
    private int studentid;
    private int courseid;

    @Column(name = "studentid", nullable = false)
    @Id
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Column(name = "courseid", nullable = false)
    @Id
    public Object getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesregistrationPK that = (CoursesregistrationPK) o;
        return Objects.equals(studentid, that.studentid) && Objects.equals(courseid, that.courseid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentid, courseid);
    }
}