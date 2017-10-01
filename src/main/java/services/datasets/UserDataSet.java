package services.datasets;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class UserDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    private Integer role; // здесь будем хранить отметку о роли юзера: пользователь или администратор

    public UserDataSet() {
    }

    public UserDataSet(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDataSet(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDataSet(long id, String name) {
        this.id = id;
        this.name = name;
        this.password = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
