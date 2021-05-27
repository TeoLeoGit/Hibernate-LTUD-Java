package pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@Table(name = "ministries", schema = "coursesregistrationdb", catalog = "")
public class Ministry {
    private int id;
    private int ministryid;
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
    @Column(name = "ministryid", nullable = true)
    public int getMinistryid() {
        return ministryid;
    }

    public void setMinistryid(int ministryid) {
        this.ministryid = ministryid;
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
        Ministry ministry = (Ministry) o;
        return Objects.equals(id, ministry.id) && Objects.equals(ministryid, ministry.ministryid) && Objects.equals(firstname, ministry.firstname) && Objects.equals(lastname, ministry.lastname) && Objects.equals(dayofbirth, ministry.dayofbirth) && Objects.equals(email, ministry.email) && Objects.equals(address, ministry.address) && Objects.equals(phone, ministry.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ministryid, firstname, lastname, dayofbirth, email, address, phone);
    }
}
