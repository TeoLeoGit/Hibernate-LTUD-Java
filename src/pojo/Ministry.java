package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

public class Ministry {
    private int id;
    private int ministryid;
    private String firstname;
    private String lastname;
    private Date dayofbirth;
    private String email;
    private String address;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinistryid() {
        return ministryid;
    }

    public void setMinistryid(int ministryid) {
        this.ministryid = ministryid;
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
        return dayofbirth;
    }

    public void setDayofbirth(Date dayofbirth) {
        this.dayofbirth = dayofbirth;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ministry ministry = (Ministry) o;
        return Objects.equals(id, ministry.id) && Objects.equals(ministryid, ministry.ministryid) && Objects.equals(firstname, ministry.firstname) && Objects.equals(lastname, ministry.lastname) && Objects.equals(dayofbirth, ministry.dayofbirth) && Objects.equals(email, ministry.email) && Objects.equals(address, ministry.address) && Objects.equals(phone, ministry.phone);
    }

    public int hashCode() {
        return Objects.hash(id, ministryid, firstname, lastname, dayofbirth, email, address, phone);
    }
}
