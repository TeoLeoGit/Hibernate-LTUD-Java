package pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "subjects", schema = "coursesregistrationdb", catalog = "")
public class Subject {
    private int id;
    private String subjectname;
    private int credits;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subjectname", nullable = false, length = 30)
    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    @Basic
    @Column(name = "credits", nullable = true)
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(subjectname, subject.subjectname) && Objects.equals(credits, subject.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectname, credits);
    }
}
