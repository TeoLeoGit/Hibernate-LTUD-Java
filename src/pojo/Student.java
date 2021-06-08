package pojo;

import java.sql.Date;
import java.util.Objects;

public class Student {
    private int id;
    private String username;
    private String password;
    private int studentId;
    private String firstname;
    private String lastname;
    private Date dayOfBirth;
    private String email;
    private String address;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username =  username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentid) {
        this.studentId = studentid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDayofbirth() {
        return dayOfBirth;
    }

    public void setDayofbirth(Date dayofbirth) {
        this.dayOfBirth = dayofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
