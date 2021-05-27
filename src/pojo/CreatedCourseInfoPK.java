package pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CreatedCourseInfoPK implements Serializable {
    private int ministryid;
    private int courseid;

    @Column(name = "ministryid", nullable = false)
    @Id
    public int getMinistryid() {
        return ministryid;
    }

    public void setMinistryid(int ministryid) {
        this.ministryid = ministryid;
    }

    @Column(name = "courseid", nullable = false)
    @Id
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedCourseInfoPK that = (CreatedCourseInfoPK) o;
        return Objects.equals(ministryid, that.ministryid) && Objects.equals(courseid, that.courseid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ministryid, courseid);
    }
}
