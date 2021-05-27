package pojo;

import javax.persistence.*;
import java.util.Objects;

public class Subject {
    private int id;
    private String subjectname;
    private int credits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(subjectname, subject.subjectname) && Objects.equals(credits, subject.credits);
    }

    public int hashCode() {
        return Objects.hash(id, subjectname, credits);
    }
}
