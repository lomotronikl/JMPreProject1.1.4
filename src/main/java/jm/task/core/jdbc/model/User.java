package jm.task.core.jdbc.model;

import javax.persistence.*;

@NamedNativeQuery(name = "get_all_users", query = "select * from users",resultClass=User.class)

@Entity
@Table (name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "lastName")
    private String lastName;

    @Column (name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("id=");
        stringBuilder.append(id).append(", ");
        stringBuilder.append(" name=").append(name).append(", ");
        stringBuilder.append(" last name=").append(lastName).append(", ");
        stringBuilder.append(" age=").append(age);

        return stringBuilder.toString();
    }
}
