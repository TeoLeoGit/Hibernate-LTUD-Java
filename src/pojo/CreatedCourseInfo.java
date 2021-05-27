package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

public class CreatedCourseInfo {
    private int ministryid;
    private int courseid;
    private Date createdate;

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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedCourseInfo that = (CreatedCourseInfo) o;
        return Objects.equals(ministryid, that.ministryid) && Objects.equals(courseid, that.courseid) && Objects.equals(createdate, that.createdate);
    }

    public int hashCode() {
        return Objects.hash(ministryid, courseid, createdate);
    }
}
