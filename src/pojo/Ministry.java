package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ministry {
    private int id;
    private String username;
    private String password;
    private int ministryId;
    private String firstname;
    private String lastname;
    private Date dayOfBirth;
    private String email;
    private String address;
    private String phone;
    private Set<CreatedCourseInfo> createdCourses = new HashSet<CreatedCourseInfo>(0);

    public Set<CreatedCourseInfo> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(Set<CreatedCourseInfo> createdCourses) {
        this.createdCourses = createdCourses;
    }

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

    public int getMinistryid() {
        return ministryId;
    }

    public void setMinistryid(int ministryid) {
        this.ministryId = ministryid;
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
