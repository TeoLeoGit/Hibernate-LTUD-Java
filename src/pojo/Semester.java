package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@Table(name = "semesters", schema = "coursesregistrationdb", catalog = "")
public class Semester {
    private int id;
    private String semestername;
    private String semesteryear;
    private Date startdate;
    private Date enddate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "semestername", nullable = false, length = 30)
    public String getSemestername() {
        return semestername;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername;
    }

    @Basic
    @Column(name = "semesteryear", nullable = true)
    public String getSemesteryear() {
        return semesteryear;
    }

    public void setSemesteryear(String semesteryear) {
        this.semesteryear = semesteryear;
    }

    @Basic
    @Column(name = "startdate", nullable = true)
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate", nullable = true)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id) && Objects.equals(semestername, semester.semestername) && Objects.equals(semesteryear, semester.semesteryear) && Objects.equals(startdate, semester.startdate) && Objects.equals(enddate, semester.enddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semestername, semesteryear, startdate, enddate);
    }
}
