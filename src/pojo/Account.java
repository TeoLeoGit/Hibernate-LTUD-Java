package pojo;

import javax.persistence.*;
import java.util.Objects;

public class Account {
    private int id;
    private String username;
    private String password;
    private Integer type;

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
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer usertype) {
        this.type = usertype;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account accounts = (Account) o;
        return Objects.equals(id, accounts.id) && Objects.equals(username, accounts.username) && Objects.equals(password,
                accounts.password) && Objects.equals(type, accounts.type);

    }

    public int hashCode() {
        return Objects.hash(id, username, password, type);
    }
}
