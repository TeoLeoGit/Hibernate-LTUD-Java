package pojo;

import java.sql.Date;
import java.util.Objects;

public class Courseregistrationsession {
    private int id;
    private Date startdate;
    private Date enddate;
    private Semester semester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courseregistrationsession that = (Courseregistrationsession) o;
        return Objects.equals(id, that.id) && Objects.equals(startdate, that.startdate) && Objects.equals(enddate, that.enddate);
    }

    public int hashCode() {
        return Objects.hash(id, startdate, enddate);
    }
}
