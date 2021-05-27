package pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "courses", schema = "coursesregistrationdb", catalog = "")
public class Course {
    private int id;
    private String teachername;
    private String classroom;
    private String dayofweek;
    private String shift;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "teachername", nullable = false, length = 30)
    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    @Basic
    @Column(name = "classroom", nullable = false, length = 20)
    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Basic
    @Column(name = "dayofweek", nullable = false, length = 10)
    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    @Basic
    @Column(name = "shift", nullable = false, length = 20)
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(teachername, course.teachername) && Objects.equals(classroom, course.classroom) && Objects.equals(dayofweek, course.dayofweek) && Objects.equals(shift, course.shift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teachername, classroom, dayofweek, shift);
    }
}
