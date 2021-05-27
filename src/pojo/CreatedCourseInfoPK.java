package pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CreatedCourseInfoPK implements Serializable {
    private int ministryid;
    private int courseid;

    public int getMinistryid() {
        return ministryid;
    }

    public void setMinistryid(int ministryid) {
        this.ministryid = ministryid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedCourseInfoPK that = (CreatedCourseInfoPK) o;
        return Objects.equals(ministryid, that.ministryid) && Objects.equals(courseid, that.courseid);
    }

    public int hashCode() {
        return Objects.hash(ministryid, courseid);
    }
}
