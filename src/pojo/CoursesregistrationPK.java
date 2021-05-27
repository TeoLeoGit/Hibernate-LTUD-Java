package pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CoursesregistrationPK implements Serializable {
    private int studentid;
    private int courseid;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public Object getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesregistrationPK that = (CoursesregistrationPK) o;
        return Objects.equals(studentid, that.studentid) && Objects.equals(courseid, that.courseid);
    }

    public int hashCode() {
        return Objects.hash(studentid, courseid);
    }
}