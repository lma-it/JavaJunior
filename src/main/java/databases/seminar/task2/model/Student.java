package databases.seminar.task2.model;

import databases.seminar.task2.util.StudentDB;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name="students", schema = "studentsDB")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    public Student(){}

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public static Student createNewStudent(){
        return new Student(updateName(), updateAge());
    }

    public static int updateAge(){
        return new Random().nextInt(20, 29);
    }

    public static String updateName(){
        return StudentDB.getRandomName();
    }


    @Override
    public String toString() {
        return String.format("Id: %s, Имя: %s, Возраст: %s", id, name, age);
    }

}
