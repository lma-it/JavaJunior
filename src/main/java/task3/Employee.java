package task3;

import java.util.UUID;

@Table(name = "users")
public class Employee {

    @Column(name = "id", primaryKey = true)
    protected UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
