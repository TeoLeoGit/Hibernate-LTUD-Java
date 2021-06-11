package pojo;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Class {
    private int classId;
    private String classname;
    private int numberOfStudent;
    private int maleNumber;
    private int femaleNumber;
    private Set<Student> students = new HashSet<Student>(0);

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getMaleNumber() {
        return maleNumber;
    }

    public void setMaleNumber(int maleNumber) {
        this.maleNumber = maleNumber;
    }

    public int getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(int femaleNumber) {
        this.femaleNumber =  femaleNumber;
    }
}
