package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@Table(name = "coursescreate", schema = "coursesregistrationdb", catalog = "")
//@IdClass(CreatedCourseInfoPK.class)
public class CreatedCourseInfo {
    private int ministryid;
    private int courseid;
    private Date createdate;

    @Id
    @Column(name = "ministryid", nullable = false)
    public int getMinistryid() {
        return ministryid;
    }

    public void setMinistryid(int ministryid) {
        this.ministryid = ministryid;
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
    @Column(name = "createdate", nullable = false)
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedCourseInfo that = (CreatedCourseInfo) o;
        return Objects.equals(ministryid, that.ministryid) && Objects.equals(courseid, that.courseid) && Objects.equals(createdate, that.createdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ministryid, courseid, createdate);
    }
}
