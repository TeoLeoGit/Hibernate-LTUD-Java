package pojo;

import javax.persistence.*;
import java.util.Objects;

public class Course {
    private int id;
    private String teachername;
    private String classroom;
    private String dayofweek;
    private String shift;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(teachername, course.teachername) && Objects.equals(classroom, course.classroom) && Objects.equals(dayofweek, course.dayofweek) && Objects.equals(shift, course.shift);
    }

    public int hashCode() {
        return Objects.hash(id, teachername, classroom, dayofweek, shift);
    }
}
