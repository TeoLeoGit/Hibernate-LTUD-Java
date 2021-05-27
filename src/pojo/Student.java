package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@Table(name = "students", schema = "coursesregistrationdb", catalog = "")
public class Student {
    private int id;
    private int studentid;
    private String firstname;
    private String lastname;
    private Date dayofbirth;
    private String email;
    private String address;
    private String phone;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "studentid", nullable = true)
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 30)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "dayofbirth", nullable = false)
    public Date getDayofbirth() {
        return dayofbirth;
    }

    public void setDayofbirth(Date dayofbirth) {
        this.dayofbirth = dayofbirth;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 30)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(studentid, student.studentid) && Objects.equals(firstname, student.firstname) && Objects.equals(lastname, student.lastname) && Objects.equals(dayofbirth, student.dayofbirth) && Objects.equals(email, student.email) && Objects.equals(address, student.address) && Objects.equals(phone, student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentid, firstname, lastname, dayofbirth, email, address, phone);
    }
}
