package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Semester {
    private int id;
    private String semestername;
    private String semesteryear;
    private Date startdate;
    private Date enddate;
    private Set<Courseregistrationsession> coursesRegSession = new HashSet<Courseregistrationsession>(0);
    private Set<Course> semCourses = new HashSet<Course>(0);

    public Set<Course> getSemCourses() {
        return semCourses;
    }

    public void setSemCourses(Set<Course> courses) {
        this.semCourses = courses;
    }

    public Set<Courseregistrationsession> getCoursesRegSession() {
        return coursesRegSession;
    }

    public void setCoursesRegSession(Set<Courseregistrationsession> coursesRegSession) {
        this.coursesRegSession = coursesRegSession;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemestername() {
        return semestername;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername;
    }

    public String getSemesteryear() {
        return semesteryear;
    }

    public void setSemesteryear(String semesteryear) {
        this.semesteryear = semesteryear;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id) && Objects.equals(semestername, semester.semestername) && Objects.equals(semesteryear, semester.semesteryear) && Objects.equals(startdate, semester.startdate) && Objects.equals(enddate, semester.enddate);
    }

    public int hashCode() {
        return Objects.hash(id, semestername, semesteryear, startdate, enddate);
    }
}
