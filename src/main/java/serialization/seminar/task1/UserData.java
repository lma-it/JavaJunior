package serialization.seminar.task1;

import java.io.Serializable;

public class UserData implements Serializable {

    private final String name;
    private final int age;
    transient String password;

    public UserData(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s\nВозраст: %s\nПароль: %s\n", this.name, this.age, this.password);
    }
}
