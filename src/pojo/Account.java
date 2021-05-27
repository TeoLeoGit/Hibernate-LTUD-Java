package pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "accounts", schema = "coursesregistrationdb", catalog = "")
public class Account {
    private int id;
    private String username;
    private String password;
    private Integer type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "userpw", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "usertype", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer usertype) {
        this.type = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account accounts = (Account) o;
        return Objects.equals(id, accounts.id) && Objects.equals(username, accounts.username) && Objects.equals(password,
                accounts.password) && Objects.equals(type, accounts.type);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, type);
    }
}
